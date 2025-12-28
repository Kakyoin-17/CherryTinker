package com.kakyoin17.cherrytinker.content;

import com.kakyoin17.cherrytinker.Cherrytinker;
import com.kakyoin17.cherrytinker.custom.CherryGemItem;
import com.kakyoin17.cherrytinker.custom.EnchantedGoldenCarrotItem;
import com.kakyoin17.cherrytinker.tool.ToolDefinitions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;


public class ModItems {
    //注册器
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,Cherrytinker.MOD_ID);
    
    //食物
    public static final RegistryObject<Item> CHERRY = ITEMS.register("cherry",
            () -> new ItemNameBlockItem(ModBlocks.CHERRY_BUSH.get(),new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(4).saturationMod(1f).build())));
    public static final RegistryObject<Item> CHERRY_JUICE  = ITEMS.register("cherry_juice",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(3).saturationMod(2f).alwaysEat()
                            .effect(new MobEffectInstance(MobEffects.HEAL,1),1.0F).build())));
    public static final RegistryObject<Item> ENCHANTED_GOLDEN_CARROT = ITEMS.register("enchanted_golden_carrot",
            () -> new EnchantedGoldenCarrotItem(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .food(new FoodProperties.Builder().nutrition(6).saturationMod(20f)
                            .effect(new MobEffectInstance(MobEffects.NIGHT_VISION,18000,0),1.0F)
                            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 18000, 0), 1.0F)
                            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 18000, 3), 1.0F ).build())));
    //材料
    public static final RegistryObject<Item> CHERRYGEM  = ITEMS.register("cherrygem" ,
            () -> new CherryGemItem(new Item.Properties()));
    public static final RegistryObject<Item> ENCHANTEDGOLD  = ITEMS.register("enchantedgold" ,
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> MONSTER_CLUSTER  = ITEMS.register("monster_cluster" ,
            () -> new Item(new Item.Properties()));
    //工具
    private static final Item.Properties TOOL = (new Item.Properties()).stacksTo(1);
    public static final RegistryObject<ModifiableItem> PAS = ITEMS.register("pas", () -> new ModifiableItem(TOOL, ToolDefinitions.PAS));
    public static final RegistryObject<ModifiableItem> SK = ITEMS.register("sk", () -> new ModifiableItem(TOOL,ToolDefinitions.SK));
    //部件
    private static final Item.Properties PART =(new Item.Properties());
    public static final RegistryObject<ToolPartItem> SK_BLADE = ITEMS.register("sk_blade", () -> new ToolPartItem(PART, HeadMaterialStats.ID));
    //铸模
    public static final ItemDeferredRegisterExtension CAST = new ItemDeferredRegisterExtension(Cherrytinker.MOD_ID);
    public static final CastItemObject SK_BLADE_CAST = CAST.registerCast("sk_blade",new Item.Properties());


































    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
