package com.kakyoin17.cherrytinker.datagen;

import com.kakyoin17.cherrytinker.Cherrytinker;
import com.kakyoin17.cherrytinker.content.ModItems; // 引用您刚才注册物品的类
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Cherrytinker.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //
        basicItem(ModItems.CHERRY.get());
    }
}