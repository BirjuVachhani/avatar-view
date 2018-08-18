package com.birjuvachhani.avatarview;

import android.graphics.LinearGradient;
import android.graphics.Shader;

/**
 * Created by Birju Vachhani on 21-07-2018.
 */
public class Gradient {
    private int startColor = AvatarView.Defaults.BACKGROUND_COLOR;
    private int endColor = AvatarView.Defaults.BACKGROUND_COLOR;
    private int mode = Mode.CLAMP;
    private int steps = 0;

    public Gradient() {
    }

    public Gradient(int startColor, int endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
    }

    public Gradient(int startColor, int endColor, int mode) {
        this.startColor = startColor;
        this.endColor = endColor;
        this.mode = mode;
    }

    public Gradient(int startColor, int endColor, int mode, int steps) {
        this.startColor = startColor;
        this.endColor = endColor;
        this.mode = mode;
        this.steps = steps;
    }

    public Gradient(int startColor, int endColor, int mode, int steps, boolean reversedGradient) {
        this.startColor = startColor;
        this.endColor = endColor;
        this.mode = mode;
        this.steps = steps;
    }

    public int getStartColor() {
        return startColor;
    }

    public void setStartColor(int startColor) {
        this.startColor = startColor;
    }

    public int getEndColor() {
        return endColor;
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        if (mode > 3 || mode < 1) mode = 1;
        this.mode = mode;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public Shader getShader(int circleRadius, boolean reversedGradient) {
        int tempStartColor = startColor;
        int tempEndColor = endColor;
        if (reversedGradient) {
            //swapping colors
            tempStartColor = endColor;
            tempEndColor = startColor;
        }

        Shader shader;
        int stepSize = steps != 0 && steps <= 100 ? (circleRadius * 2) / steps : circleRadius * 2;
        switch (mode) {
            case 2:
                shader = new LinearGradient(0, 0, stepSize, stepSize, tempStartColor, tempEndColor, Shader.TileMode.MIRROR);
                break;
            case 3:
                shader = new LinearGradient(0, 0, stepSize, stepSize, tempStartColor, tempEndColor, Shader.TileMode.REPEAT);
                break;
            default:
                shader = new LinearGradient(0, 0, circleRadius * 2, circleRadius * 2, tempStartColor, tempEndColor, Shader.TileMode.CLAMP);
        }
        return shader;
    }

    public class Mode {
        public static final int CLAMP = 1;
        public static final int MIRROR = 2;
        public static final int REPEAT = 3;
    }
}
