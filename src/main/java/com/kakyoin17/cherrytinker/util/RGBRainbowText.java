package com.kakyoin17.cherrytinker.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

/**
 * Forge 1.20.1 动态 RGB 渐变字体工具类
 * 升级自: csdy 的 RainbowText
 */
public class RGBRainbowText {

    private static final int[] PALETTE_DEFAULT = {0xFF5555, 0xFFAA00, 0xFFFF55, 0x55FF55, 0x55FFFF, 0x5555FF, 0xFF55FF};
    private static final int[] PALETTE_2 = {0xFF5555, 0xAA0000, 0xFFFF55, 0xFFAA00, 0xFF55FF, 0x55FFFF};
    private static final int[] PALETTE_3 = {0xFF5555, 0xAA0000, 0xAA00AA, 0xFF55FF};
    private static final int[] PALETTE_4 = {0x00AAAA, 0xAA0000, 0x5555FF};
    private static final int[] PALETTE_6 = {0x5555FF, 0x0000AA};
    private static final int[] PALETTE_17 = {0xFFAA00, 0xFFFF55, 0xFF55FF, 0xAA00AA}; // Kakyoin 配色

    public RGBRainbowText() {
    }
    /**
     * 核心方法：生成插值渐变文本
     * @param input 输入文字
     * @param colors 颜色数组 (Hex int)
     * @param speed 速度 (毫秒/周期)，数值越小越快
     * @param step 每个字符的跨度 (推荐 0.1 - 0.5)
     */
    public static MutableComponent formatting(String input, int[] colors, int speed, double step) {
        if (speed <= 0) speed = 1;

        MutableComponent root = Component.literal(""); // 1.20.1 根组件
        long time = System.currentTimeMillis();

        // 避免数组过短导致的错误
        if (colors.length == 0) return Component.literal(input);
        if (colors.length == 1) return Component.literal(input).withStyle(Style.EMPTY.withColor(colors[0]));

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            // 1. 计算当前字符在颜色周期中的位置 (0.0 ~ colors.length)
            // time / speed: 时间驱动
            // i * step: 空间错位
            double position = ((double)time / speed) + (i * step);

            // 2. 取模，确保位置在数组长度范围内
            double progress = position % colors.length;
            if (progress < 0) progress += colors.length;

            // 3. 确定当前处于哪两个颜色之间
            int index1 = (int) progress; // 当前颜色索引
            int index2 = (index1 + 1) % colors.length; // 下一个颜色索引

            // 4. 计算插值比例 (0.0 ~ 1.0)
            float factor = (float) (progress - index1);

            // 5. 执行 RGB 线性插值
            int color = interpolateColor(colors[index1], colors[index2], factor);

            // 6. 组装组件
            Style style = Style.EMPTY.withColor(TextColor.fromRgb(color));
            root.append(Component.literal(String.valueOf(c)).withStyle(style));
        }

        return root;
    }

    /**
     * 辅助方法：在两个颜色之间进行平滑混合
     */
    private static int interpolateColor(int color1, int color2, float factor) {
        // 提取 color1 的 R, G, B
        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >> 8) & 0xFF;
        int b1 = color1 & 0xFF;

        // 提取 color2 的 R, G, B
        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;

        // 混合
        int r = (int) (r1 + (r2 - r1) * factor);
        int g = (int) (g1 + (g2 - g1) * factor);
        int b = (int) (b1 + (b2 - b1) * factor);

        return (r << 16) | (g << 8) | b;
    }

    // --- 快捷调用方法  ---

    public static MutableComponent makeColour(String input) {
        // 速度 300ms(每个颜色停留时间)，步长 0.2
        return formatting(input, PALETTE_DEFAULT, 300, 0.2);
    }

    public static MutableComponent makeColour2(String input) {
        return formatting(input, PALETTE_2, 300, 0.2);
    }

    public static MutableComponent makeColour3(String input) {
        // 暗黑风，步长稍微大一点
        return formatting(input, PALETTE_3, 400, 0.3);
    }

    public static MutableComponent makeColour4(String input) {
        return formatting(input, PALETTE_4, 400, 0.3);
    }


    //“全饱和度彩虹”
    public static MutableComponent makeColour5(String input) {
        return formatting(input, PALETTE_DEFAULT, 100, 0.5); // 极速流动
    }

    public static MutableComponent makeColour6(String input) {
        //蓝色<->深蓝 呼吸渐变
        return formatting(input, PALETTE_6, 1000, 0.1);
    }

    public static MutableComponent makeColour17(String input){
        //金色与紫色，高贵风格
        return formatting(input, PALETTE_17, 300, 0.25);
    }
}
