package com.kakyoin17.cherrytinker.CustomItems;

import com.kakyoin17.cherrytinker.util.ThemedRGBText;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Properties;

public class Cherry extends Item {
    public Cherry(Properties aSuper) {
        super(aSuper);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        components.add(ThemedRGBText.themeRoyal("Cherry is best"));
    }
}
