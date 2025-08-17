package com.Kakyoin17.cherrytinker.registry;

import com.Kakyoin17.cherrytinker.cherrytinker;
import com.Kakyoin17.cherrytinker.custom.CherryBushBlockItem;
import com.Kakyoin17.cherrytinker.custom.EGoldBlock;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class  ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, cherrytinker.MODID);

    // 注册一个方块，
    // new Block时候需要设置方块的一些属性，通过BlockBehaviour进行设置
    // 不同的材质可以在Material下面进行调整。例如AIR 等等通 .就可以看到，或者按住ctrl在点击Material，就可以看到可以用的Material
    // 可以看到of返回的依旧是properties的对象
    // 通过properties对象还可以设置 硬度
    // 需要正确的工具才可以采集
    // 设置的内容都可以在properties下面找到，按住ctrl点击Properties可以看到。
    // tab是之前自己自己创建的tab
    public static final RegistryObject<Block> EnchantedGold_BLOCK = registerBlock("enchantedgold_block",
            ()->new EGoldBlock(BlockBehaviour.Properties.of(Material.METAL).strength(6f).requiresCorrectToolForDrops().lightLevel(value -> 15)), ModCreativeModeTab.CHERRYTINKER_TAB);
    public static final RegistryObject<Block> CherryGem_Block = registerBlock("cherrygem_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(4f).requiresCorrectToolForDrops().lightLevel(value -> 7)),ModCreativeModeTab.CHERRYTINKER_TAB);
    // 注册一个矿物的方块，这里new的是一个Block的子类，这个类指明了是一个破坏后会掉落经验的Block
    // 如果是其他具有自己效果的Block，也可以自己去实现一个Block类。
    // 其中 new DropExperienceBlock 的第三个参数指明的是掉落经验值的范围。
    public static final RegistryObject<Block> CherryGem = registerBlock("cherry_gem",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops(),
                    UniformInt.of(17,21)), ModCreativeModeTab.CHERRYTINKER_TAB);

    public static final RegistryObject<Block> DeepslateCherryGem = registerBlock("deepslate_cherry_gem",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops(),
                    UniformInt.of(17,21)), ModCreativeModeTab.CHERRYTINKER_TAB);

    public static final RegistryObject<Block> Cherry_Bush = BLOCKS.register("cherry_bush",
            () -> new CherryBushBlockItem(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH)));













    // 注册Block，由于需要注册Block以及该Block的Item所以这里写了一个函数用于同时注册Block和Item
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registryBlockItem(name,toReturn,tab);
        return toReturn;
    }

    // 用于注册方块对应的物品，由于方块和物品是分开的，需要都注册，通过之前的ModItem.ITEMS的注册方法注册Item，使用BlockItem创建方块的Item
    private static <T extends Block>RegistryObject<Item> registryBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name,()->new BlockItem(block.get(),new Item.Properties().tab(tab)));
    }
    // 用于给总线注册。
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
