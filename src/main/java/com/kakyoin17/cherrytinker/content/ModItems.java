package com.kakyoin17.cherrytinker.content;

import com.kakyoin17.cherrytinker.Cherrytinker;
import com.kakyoin17.cherrytinker.CustomItems.Cherry;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    //
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Cherrytinker.MOD_ID);
    //
    public static final RegistryObject<Item> CHERRY = ITEMS.register("cherry",
            () -> new Cherry(new Item.Properties()));
    //
    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
