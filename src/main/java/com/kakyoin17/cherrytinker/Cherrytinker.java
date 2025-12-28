package com.kakyoin17.cherrytinker;

import com.kakyoin17.cherrytinker.content.*;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(Cherrytinker.MOD_ID)
public class Cherrytinker {

    public static final String MOD_ID = "cherrytinker";


    private static final Logger LOGGER = LogUtils.getLogger();

    public Cherrytinker(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();


        ModItems.register(modEventBus);
        ModItems.CAST.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModFluids.FLUIDS.register(modEventBus);

        ModCreativeTabs.register(modEventBus);
        ModModifiers.MODIFIERS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
