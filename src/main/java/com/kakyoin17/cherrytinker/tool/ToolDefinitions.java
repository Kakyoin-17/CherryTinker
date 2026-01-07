package com.kakyoin17.cherrytinker.tool;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import slimeknights.tconstruct.library.tools.definition.ModifiableArmorMaterial;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;

public class ToolDefinitions {
    public ToolDefinitions() {}
    public static final String MOD_ID = "cherrytinker";

    public static final ToolDefinition PAS = ToolDefinition.create(ResourceLocation.fromNamespaceAndPath(MOD_ID, "pas"));
    public static final ToolDefinition SK = ToolDefinition.create(ResourceLocation.fromNamespaceAndPath(MOD_ID, "sk"));

    public static final ModifiableArmorMaterial EXOSKELETON = ModifiableArmorMaterial.create(
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "exoskeleton"),
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            ArmorItem.Type.HELMET,
            ArmorItem.Type.CHESTPLATE,
            ArmorItem.Type.LEGGINGS,
            ArmorItem.Type.BOOTS
    );

    public static final ToolDefinition EXO_HELMET = EXOSKELETON.getArmorDefinition(ArmorItem.Type.HELMET);
    public static final ToolDefinition EXO_CHESTPLATE = EXOSKELETON.getArmorDefinition(ArmorItem.Type.CHESTPLATE);
    public static final ToolDefinition EXO_LEGGINGS = EXOSKELETON.getArmorDefinition(ArmorItem.Type.LEGGINGS);
    public static final ToolDefinition EXO_BOOTS = EXOSKELETON.getArmorDefinition(ArmorItem.Type.BOOTS);

}
