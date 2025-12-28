package com.kakyoin17.cherrytinker.datagen;

import com.kakyoin17.cherrytinker.Cherrytinker;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Cherrytinker.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        //客户端
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModLanguageProvider(packOutput, Cherrytinker.MOD_ID,"en_us"));
        generator.addProvider(event.includeClient(), new ModLanguageProvider(packOutput, Cherrytinker.MOD_ID,"zh_cn"));
        generator.addProvider(event.includeClient(), new ModFluidTextures(packOutput, "cherrytinker"));
        generator.addProvider(event.includeClient(), new ModBucketModels(packOutput, "cherrytinker"));







        //服务端
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput));
        generator.addProvider(event.includeServer(), new ModWorldGenProvider(packOutput, event.getLookupProvider()));
        //TAG
        generator.addProvider(event.includeServer(), new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput));








    }
}
