package com.kakyoin17.cherrytinker.content;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class TintedFluidType extends FluidType {
    private final int tint;
    private final ResourceLocation still;
    private final ResourceLocation flowing;

    public TintedFluidType(Properties properties, int tint, ResourceLocation still, ResourceLocation flowing) {
        super(properties);
        this.tint = tint;
        this.still = still;
        this.flowing = flowing;
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public int getTintColor() {
                return tint;
            }

            @Override
            public @NotNull ResourceLocation getStillTexture() {
                return still;
            }

            @Override
            public @NotNull ResourceLocation getFlowingTexture() {
                return flowing;
            }
        });
    }
}
