package com.Kakyoin17.cherrytinker.registry;

import com.Kakyoin17.cherrytinker.cherrytinker;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.fluid.UnplaceableFluid;
import slimeknights.mantle.fluid.texture.AbstractFluidTextureProvider;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.fluids.data.FluidBucketModelProvider;

import javax.annotation.Nullable;

public class ModFluids {
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(cherrytinker.MODID);

    public static FlowingFluidObject<ForgeFlowingFluid> cherryJuice = FLUIDS.register("cherry_juice").type(cool()).bucket().block(Material.WATER).flowing();

    public static FlowingFluidObject<ForgeFlowingFluid> moltenCherrygem = FLUIDS.register("molten_cherrygem").type(hot().lightLevel(12)).bucket().block(Material.LAVA).flowing();
    public static final FluidObject<UnplaceableFluid> moltenEnchantedgold = FLUIDS.register("molten_enchantedgold").type(hot().lightLevel(15)).bucket(ModItems.MoltenEnchantedgoldBucket).unplacable();

    public static  FlowingFluidObject<ForgeFlowingFluid> mooncherryfuel = FLUIDS.register("moon_cherryfuel").type(cool().lightLevel(7)).bucket().block(Material.LAVA).flowing();
    public static  FlowingFluidObject<ForgeFlowingFluid> suncherryfuel = FLUIDS.register("sun_cherryfuel").type(hot().lightLevel(15)).bucket().block(Material.LAVA).flowing();

    private static FluidType.Properties cool() {
        return FluidType.Properties.create()
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY);
    }

    private static FluidType.Properties hot() {
        return FluidType.Properties.create().density(2000).viscosity(10000).temperature(1000)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA);
    }

    public static class ModFluidTextures extends AbstractFluidTextureProvider {
        public  ModFluidTextures(DataGenerator generator, @Nullable String modId){
            super(generator,modId);
        }

        @Override
        public void addTextures(){
            texture(cherryJuice).textures(new ResourceLocation("tconstruct:fluid/food/stew/"), false, false).color(0xFFFFD4D7);

            texture(moltenCherrygem).textures(new ResourceLocation("tconstruct:fluid/molten/ore/diamond/"), false, false).color(0xFFE22F3A);
            texture(moltenEnchantedgold).textures(new ResourceLocation("tconstruct:fluid/molten/ore/gold/"), false, false).color(0xFFC97AC9);

            texture(mooncherryfuel).textures(new ResourceLocation("cherrytinker:block/fluid/moon_cherryfuel/"),false,false);
            texture(suncherryfuel).textures(new ResourceLocation("cherrytinker:block/fluid/sun_cherryfuel/"), false, false);

        }

        @Override
        public String getName() {
            return "CherryTinker's Fluid Textures";
        }
    }
    public static class CherrytinkerBucketModels extends FluidBucketModelProvider {
        public CherrytinkerBucketModels(DataGenerator generator, String modId) {
            super(generator, modId);
        }
    }
}
