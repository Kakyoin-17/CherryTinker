package com.kakyoin17.cherrytinker.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

/**
 * 基于 RGB 插值的高级动态渐变方案 - Forge 1.20.1
 */
public class ThemedRGBText {

    // --- 颜色调色板 (HEX 格式) ---

    // 1. 七彩虹桥
    private static final int[] PALETTE_PRISM = {0xFF5555, 0xFFAA00, 0xFFFF55, 0x55FF55, 0x55FFFF, 0x5555FF, 0xFF55FF};

    // 2. 冰与火之歌
    private static final int[] PALETTE_ELEMENTAL = {0xFF5555, 0xAA0000, 0xFFFF55, 0x55FFFF, 0x5555FF};

    // 3. 虚空裂隙
    private static final int[] PALETTE_VOID = {0xAA0000, 0x550000, 0x5500AA, 0xAA00AA};

    // 4. 深海余烬
    private static final int[] PALETTE_ABYSS = {0x00AAAA, 0x005555, 0xAA0000, 0x5555FF};

    // 5. 幽蓝呼吸
    private static final int[] PALETTE_PULSE_BLUE = {0x5555FF, 0x0000AA, 0x5555FF};

    // 6. 皇家威严
    private static final int[] PALETTE_ROYAL = {0xFFAA00, 0xFFFF55, 0xFF55FF, 0xAA00AA};

    // 7. 附魔神光
    private static final int[] PALETTE_ENCHANT = {0x8000FF, 0xB455FF, 0xFF55FF, 0x55FFFF, 0xB455FF};

    /**
     * 基础逻辑：RGB 线性插值渐变
     */
    public static MutableComponent apply(String input, int[] palette, int speed, double step) {
        MutableComponent root = Component.literal("");
        long time = System.currentTimeMillis();

        for (int i = 0; i < input.length(); i++) {
            double progress = (((double) time / speed) + (i * step)) % palette.length;
            if (progress < 0) progress += palette.length;

            int i1 = (int) progress;
            int i2 = (i1 + 1) % palette.length;
            float factor = (float) (progress - i1);

            int color = lerp(palette[i1], palette[i2], factor);
            root.append(Component.literal(String.valueOf(input.charAt(i)))
                    .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(color)).withBold(true)));
        }
        return root;
    }

    private static int lerp(int c1, int c2, float f) {
        int r = (int) (((c1 >> 16) & 0xFF) + (((c2 >> 16) & 0xFF) - ((c1 >> 16) & 0xFF)) * f);
        int g = (int) (((c1 >> 8) & 0xFF) + (((c2 >> 8) & 0xFF) - ((c1 >> 8) & 0xFF)) * f);
        int b = (int) ((c1 & 0xFF) + ((c2 & 0xFF) - (c1 & 0xFF)) * f);
        return (r << 16) | (g << 8) | b;
    }

    // --- 主题化方案方法 ---

    /** 七彩虹桥：经典的 RGB 滚动 */
    public static MutableComponent themePrism(String input) {
        return apply(input, PALETTE_PRISM, 350, 0.2);
    }

    /** 冰火元素：冷暖色调的强烈对比 */
    public static MutableComponent themeElemental(String input) {
        return apply(input, PALETTE_ELEMENTAL, 400, 0.25);
    }

    /** 虚空裂隙：暗红色与深紫色的邪恶交织 */
    public static MutableComponent themeVoid(String input) {
        return apply(input, PALETTE_VOID, 600, 0.3);
    }

    /** 深海余烬：深青色与血色的碰撞 */
    public static MutableComponent themeAbyss(String input) {
        return apply(input, PALETTE_ABYSS, 500, 0.2);
    }

    /** 幽蓝脉冲：像深海灯箱一样的呼吸效果 */
    public static MutableComponent themePulseBlue(String input) {
        return apply(input, PALETTE_PULSE_BLUE, 800, 0.1);
    }

    /** 皇家尊贵：金黄与紫罗兰的华丽感 */
    public static MutableComponent themeRoyal(String input) {
        return apply(input, PALETTE_ROYAL, 450, 0.2);
    }

    /** 附魔神光：完美复刻附魔物品的流光质感 */
    public static MutableComponent themeEnchant(String input) {
        return apply(input, PALETTE_ENCHANT, 500, 0.15);
    }

}
