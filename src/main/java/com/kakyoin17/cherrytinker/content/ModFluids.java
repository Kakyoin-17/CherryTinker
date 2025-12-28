package com.kakyoin17.cherrytinker.content;

import com.kakyoin17.cherrytinker.Cherrytinker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FlowingFluidObject;

public class ModFluids {
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(Cherrytinker.MOD_ID);
    //
    public static final FlowingFluidObject<ForgeFlowingFluid> cherryJuice = FLUIDS.register("cherry_juice")
            .type(() -> new TintedFluidType(
                    FluidType.Properties.create().canHydrate(true),
                    0xFFE83E8C,
                    ResourceLocation.fromNamespaceAndPath("tconstruct", "fluid/food/stew/still"),
                    ResourceLocation.fromNamespaceAndPath("tconstruct", "fluid/food/stew/flowing")
            ))
            .bucket()
            .block(MapColor.WATER, 0)
            .flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenCherrygem = FLUIDS.register("molten_cherrygem")
            .type(() -> new TintedFluidType(
                    FluidType.Properties.create()
                            .density(2000)
                            .viscosity(10000)
                            .temperature(1000)
                            .lightLevel(12),
                    0xFFD32F2F,
                    ResourceLocation.fromNamespaceAndPath("tconstruct", "fluid/molten/ore/diamond/still"),
                    ResourceLocation.fromNamespaceAndPath("tconstruct", "fluid/molten/ore/diamond/flowing")
            ))
            .bucket()
            .block(MapColor.COLOR_RED, 12)
            .flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenEnchantedgold = FLUIDS.register("molten_enchantedgold")
            .type(() -> new TintedFluidType(
                    FluidType.Properties.create()
                            .density(2000)
                            .viscosity(10000)
                            .temperature(1000)
                            .lightLevel(15),
                    0xFFC97AC9,
                    ResourceLocation.fromNamespaceAndPath("tconstruct", "fluid/molten/ore/gold/still"),
                    ResourceLocation.fromNamespaceAndPath("tconstruct", "fluid/molten/ore/gold/flowing")
            ))
            .bucket()
            .block(MapColor.GOLD, 15)
            .flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> mooncherryfuel = FLUIDS.register("moon_cherryfuel")
            .type(() -> new TintedFluidType(
                    FluidType.Properties.create()
                            .density(2000)
                            .viscosity(10000)
                            .temperature(1000)
                            .lightLevel(7),
                    0xFFFFFFFF,
                    ResourceLocation.fromNamespaceAndPath("cherrytinker", "fluid/moon_cherryfuel/still"),
                    ResourceLocation.fromNamespaceAndPath("cherrytinker", "fluid/moon_cherryfuel/flowing")
            ))
            .bucket()
            .block(MapColor.COLOR_BLUE, 15)
            .flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> suncherryfuel = FLUIDS.register("sun_cherryfuel")
            .type(() -> new TintedFluidType(
                    FluidType.Properties.create()
                            .density(2000)
                            .viscosity(10000)
                            .temperature(1000)
                            .lightLevel(15),
                    0xFFFFFFFF,
                    ResourceLocation.fromNamespaceAndPath("cherrytinker", "fluid/sun_cherryfuel/still"),
                    ResourceLocation.fromNamespaceAndPath("cherrytinker", "fluid/sun_cherryfuel/flowing")
            ))
            .bucket()
            .block(MapColor.FIRE, 15)
            .flowing();
}
