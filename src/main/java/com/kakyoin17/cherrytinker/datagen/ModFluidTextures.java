package com.kakyoin17.cherrytinker.datagen;

import com.kakyoin17.cherrytinker.content.ModFluids;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import slimeknights.mantle.fluid.texture.AbstractFluidTextureProvider;

public class ModFluidTextures extends AbstractFluidTextureProvider {
    public ModFluidTextures(PackOutput packOutput, String modId) {
        super(packOutput, modId);
    }
    @Override
    public void addTextures() {
        //
        texture(ModFluids.moltenCherrygem)
                .textures(ResourceLocation.fromNamespaceAndPath("tconstruct", "block/fluid/molten/"), false,true)
                .color(0xFFE22F3A); // 务必与 FluidType 中的颜色一致
        texture(ModFluids.moltenEnchantedgold)
                .textures(ResourceLocation.fromNamespaceAndPath("tconstruct", "block/fluid/molten/"), false,true)
                .color(0xFFC97AC9);
        texture(ModFluids.cherryJuice)
                .textures(ResourceLocation.fromNamespaceAndPath("tconstruct", "block/fluid/stew/"), false,false)
                .color(0xFFFFD4D7);
        //
        texture(ModFluids.mooncherryfuel).textures(ResourceLocation.fromNamespaceAndPath("cherrytinker", "block/fluid/moon_cherryfuel/"), false,false);
        texture(ModFluids.suncherryfuel).textures(ResourceLocation.fromNamespaceAndPath("cherrytinker", "block/fluid/sun_cherryfuel/"), false,true);
    }
    @Override
    public String getName() {
        return "CherryTinker Fluid Textures";
    }
}
