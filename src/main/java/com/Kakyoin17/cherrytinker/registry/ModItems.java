package com.Kakyoin17.cherrytinker.registry;

import com.Kakyoin17.cherrytinker.cherrytinker;
import com.Kakyoin17.cherrytinker.custom.CherryGemItem;
import com.Kakyoin17.cherrytinker.custom.EnchantedGoldenCarrotItem;
import com.Kakyoin17.cherrytinker.custom.EnchantedgoldItem;
import com.Kakyoin17.cherrytinker.custom.MoltenEnchantedgoldBucketItem;
import com.Kakyoin17.tools.item.Rod;
import com.Kakyoin17.tools.toolDefinitions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.fluids.item.ContainerFoodItem;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;


public class ModItems {
    //
    public  static  final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, cherrytinker.MODID);
    public static final ItemDeferredRegisterExtension CAST = new ItemDeferredRegisterExtension(cherrytinker.MODID);
    private static final Item.Properties TOOL = (new Item.Properties()).tab(ModCreativeModeTab.CHERRYTINKER_TAB).stacksTo(1);
    private static final Item.Properties CASTS =(new Item.Properties().tab(ModCreativeModeTab.CHERRYTINKER_TAB));
    private static final Item.Properties PARTS =(new Item.Properties().tab(ModCreativeModeTab.CHERRYTINKER_TAB));
    //食物
    public static final RegistryObject<Item> Cherry = ITEMS.register("cherry",
            ()->new ItemNameBlockItem(ModBlocks.Cherry_Bush.get(),
                    new Item.Properties().tab(ModCreativeModeTab.CHERRYTINKER_TAB)
                            .food(new FoodProperties.Builder().nutrition(4).saturationMod(1f).build())));
    public static final RegistryObject<ContainerFoodItem.FluidContainerFoodItem> cherryJuiceBottle =ITEMS.register("cherry_juice",
            () -> new ContainerFoodItem.FluidContainerFoodItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(3).saturationMod(2f).alwaysEat()
                            .effect(new MobEffectInstance(MobEffects.HEAL,1),1.0F).build()).tab(ModCreativeModeTab.CHERRYTINKER_TAB),
                    () -> new FluidStack(ModFluids.cherryJuice.get(), FluidValues.BOTTLE)));
    public static final RegistryObject<Item> EnchantedGoldenCarrot = ITEMS.register("enchanted_golden_carrot",
            () -> new EnchantedGoldenCarrotItem(new Item.Properties().tab(ModCreativeModeTab.CHERRYTINKER_TAB)
                    .rarity(Rarity.EPIC)
                    .food(new FoodProperties.Builder().nutrition(6).saturationMod(20f)
                            .effect(new MobEffectInstance(MobEffects.NIGHT_VISION,18000,0),1.0F)
                            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 18000, 0), 1.0F).build())));
    //材料
    public static final RegistryObject<Item> CherryGem  = ITEMS.register("cherrygem" ,
            () -> new CherryGemItem(new Item.Properties().tab(ModCreativeModeTab.CHERRYTINKER_TAB)));
    public static final RegistryObject<Item> EnchantedGold  = ITEMS.register("enchantedgold" ,
            () -> new EnchantedgoldItem(new Item.Properties().tab(ModCreativeModeTab.CHERRYTINKER_TAB)
                    .rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> Monster_Cluster  = ITEMS.register("monster_cluster" ,
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.CHERRYTINKER_TAB)));

    //流体桶
    public static final RegistryObject<Item> MoltenEnchantedgoldBucket = ITEMS.register("molten_enchantedgold_bucket",
            () -> new MoltenEnchantedgoldBucketItem(ModFluids.moltenEnchantedgold.get(),
                    new Item.Properties().tab(ModCreativeModeTab.CHERRYTINKER_TAB)
                            .rarity(Rarity.EPIC)));
    //工具
    public static final RegistryObject<ModifiableItem> pas = ITEMS.register("pas", () -> new ModifiableItem(TOOL, toolDefinitions.PAS));
    public static final RegistryObject<ModifiableItem> sk = ITEMS.register("sk", () -> new ModifiableItem(TOOL,toolDefinitions.SK));
    public static final RegistryObject<ModifiableItem> rod = ITEMS.register("rod", () -> new Rod(TOOL,toolDefinitions.ROD));


    //部件
    public static final RegistryObject<ToolPartItem> sk_blade = ITEMS.register("sk_blade", () -> new ToolPartItem(PARTS,HeadMaterialStats.ID));

    //铸模
    public static final CastItemObject sk_blade_cast = CAST.registerCast("sk_blade",new Item.Properties().tab(ModCreativeModeTab.CHERRYTINKER_TAB));




















    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
