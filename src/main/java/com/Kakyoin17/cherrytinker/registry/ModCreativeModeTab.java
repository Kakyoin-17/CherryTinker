package com.Kakyoin17.cherrytinker.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

    public class ModCreativeModeTab {
        public static final CreativeModeTab CHERRYTINKER_TAB =new CreativeModeTab("cherrytinker") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ModItems.Cherry.get());
            }

        };
    }
