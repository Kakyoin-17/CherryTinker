package com.kakyoin17.cherrytinker.content;

import com.kakyoin17.cherrytinker.Cherrytinker;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    //
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Cherrytinker.MOD_ID);
    //
    public static final RegistryObject<CreativeModeTab> CHERRY_TAB = TABS.register("cherry_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.cherrytinker"))
            .icon(() -> new ItemStack(ModItems.CHERRY.get()))
            .displayItems((parameters, output) -> {
                for(RegistryObject<Item> item :ModItems.ITEMS.getEntries()){
                    output.accept(item.get());
                }
            }).build());
    //
    public static void register(IEventBus eventBus) {TABS.register(eventBus);}
}
