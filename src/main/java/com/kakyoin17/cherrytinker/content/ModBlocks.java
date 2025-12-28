package com.kakyoin17.cherrytinker.content;

import com.kakyoin17.cherrytinker.Cherrytinker;
import com.kakyoin17.cherrytinker.custom.CherryBushBlockItem;
import com.kakyoin17.cherrytinker.custom.EGoldBlock;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    //
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,Cherrytinker.MOD_ID);

    public static final RegistryObject<Block> ENCHANTEDGOLD_BLOCK = registerBlock("enchantedgold_block",
            () -> new EGoldBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.GOLD)
                    .strength(8f)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> 15)));
    public static final RegistryObject<Block> CHERRYGEM_BLOCK = registerBlock("cherrygem_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(6f)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> 7)));
    public static final RegistryObject<Block> CHERRY_GEM = registerBlock("cherry_gem",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(6f)
                    .requiresCorrectToolForDrops(),
                    UniformInt.of(17, 21)));
    public static final RegistryObject<Block> DEEPSLATE_CHERRY_GEM = registerBlock("deepslate_cherry_gem",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .strength(6f)
                    .requiresCorrectToolForDrops(),
                    UniformInt.of(17, 21)));
    public static final RegistryObject<Block> CHERRY_BUSH = registerBlock("cherry_bush",
            () -> new CherryBushBlockItem(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .randomTicks()
                    .noCollission()
                    .sound(SoundType.SWEET_BERRY_BUSH)
                    ));















    //
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registryBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registryBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {BLOCKS.register(eventBus);}
}
