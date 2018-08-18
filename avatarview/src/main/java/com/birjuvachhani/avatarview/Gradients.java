package com.birjuvachhani.avatarview;

import android.content.Context;
import android.graphics.Color;

/**
 * Created by Birju Vachhani on 21-07-2018.
 */
public class Gradients {
    public static final int berry_smoothie = 1;
    public static final int coastal_breeze = 2;
    public static final int deep_sea = 3;
    public static final int evening_delight = 4;
    public static final int firebrick = 5;
    public static final int fizzy_peach = 6;
    public static final int fresh_papaya = 7;
    public static final int lemon_drizzle = 8;
    public static final int mystic_mauve = 9;
    public static final int neon_glow = 10;
    public static final int new_leaf = 11;
    public static final int new_life = 12;
    public static final int ocean_blue = 13;
    public static final int october_silence = 14;
    public static final int orange_juice = 15;
    public static final int pink_sugar = 16;
    public static final int pixie_dust = 17;
    public static final int purple_lake = 18;
    public static final int reflex_silver = 19;
    public static final int sanguine = 20;
    public static final int sharp_blues = 21;
    public static final int spring_greens = 22;
    public static final int sweet_dream = 23;
    public static final int ultramarine = 24;
    public static final int victoria_purple = 25;
    private static int[] gradientList = {
            R.array.gradient_berry_smoothie,
            R.array.gradient_coastal_breeze,
            R.array.gradient_deep_sea,
            R.array.gradient_evening_delight,
            R.array.gradient_firebrick,
            R.array.gradient_fizzy_peach,
            R.array.gradient_fresh_papaya,
            R.array.gradient_lemon_drizzle,
            R.array.gradient_mystic_mauve,
            R.array.gradient_neon_glow,
            R.array.gradient_new_leaf,
            R.array.gradient_new_life,
            R.array.gradient_ocean_blue,
            R.array.gradient_october_silence,
            R.array.gradient_orange_juice,
            R.array.gradient_pink_sugar,
            R.array.gradient_pixie_dust,
            R.array.gradient_purple_lake,
            R.array.gradient_reflex_silver,
            R.array.gradient_sanguine,
            R.array.gradient_sharp_blues,
            R.array.gradient_spring_greens,
            R.array.gradient_sweet_dream,
            R.array.gradient_ultramarine,
            R.array.gradient_victoria_purple,
    };

    public static Gradient getGradient(Context context, int pos) {
        if (pos >= gradientList.length) pos = 1;
        String colors[] = context.getResources().getStringArray(gradientList[pos - 1]);
        return new Gradient(Color.parseColor(colors[0]), Color.parseColor(colors[1]));
    }
}
