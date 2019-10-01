package com.birjuvachhani.avatarview;

import android.content.Context;
import android.graphics.Shader;
import android.util.DisplayMetrics;

import androidx.annotation.ColorInt;

/**
 * Created by Birju Vachhani on 21-07-2018.
 */
public class AvatarStyle {
    @ColorInt
    private int backgroundColor = AvatarView.Defaults.BACKGROUND_COLOR;
    @ColorInt
    private int borderColor = AvatarView.Defaults.BORDER_COLOR;
    private int circleRadius;
    private int borderWidth;
    private int mode = AvatarView.Defaults.MODE;
    private DisplayMetrics displayMetrics;
    private String initials = "</>";
    private int textColor;
    private int textSize;
    private Gradient gradient;
    private boolean imageOnly = false;
    private boolean textOnTop = false;
    private boolean reversedGradient = false;

    public AvatarStyle(Context context) {
        displayMetrics = context.getResources().getDisplayMetrics();
        this.circleRadius = convertDpToPixel(AvatarView.Defaults.CIRCLE_RADIUS);
        this.borderWidth = convertDpToPixel(AvatarView.Defaults.BORDER_WIDTH);
        this.textSize = convertDpToPixel(AvatarView.Defaults.TEXT_SIZE);
        this.textColor = AvatarView.Defaults.TEXT_COLOR;
        this.gradient = Gradients.getGradient(context, 1);
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String name) {
        this.initials = InitialUtils.getInitials(name);
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = convertDpToPixel(textSize);
    }

    protected void setTextSizeDimen(int textSize) {
        this.textSize = textSize;
    }

    public Gradient getGradient() {
        return gradient;
    }

    public void setGradient(Gradient gradient) {
        this.gradient = gradient;
    }

    public Shader getShader() {
        if (gradient == null) {
            gradient = new Gradient();
        }
        return gradient.getShader(circleRadius, reversedGradient);
    }

    public boolean isGradientReversed() {
        return reversedGradient;
    }

    public void setGradientReversed(boolean reversedGradient) {
        this.reversedGradient = reversedGradient;
    }

    public boolean isImageOnly() {
        return imageOnly;
    }

    public void setImageOnly(boolean imageOnly) {
        this.imageOnly = imageOnly;
    }

    public boolean isTextOnTop() {
        return textOnTop;
    }

    public void showTextOnTop(boolean showTextOnTop) {
        this.textOnTop = showTextOnTop;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public int getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(int circleRadius) {
        this.circleRadius = convertDpToPixel(circleRadius);
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = convertDpToPixel(borderWidth);
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    private int convertDpToPixel(int dp) {
        float px = dp * ((float) displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return Math.round(px);
    }

    protected void setCircleRadiusDimens(int dimen) {
        this.circleRadius = dimen;
    }

    protected void setBorderWidthDimens(int dimen) {
        this.borderWidth = dimen;
    }

    public static class Builder {
        private AvatarStyle avatarStyle;

        public Builder(Context context) {
            avatarStyle = new AvatarStyle(context);
        }

        public AvatarStyle build() {
            return avatarStyle;
        }

        public AvatarStyle.Builder setBackgroundColor(@ColorInt int color) {
            avatarStyle.setBackgroundColor(color);
            return this;
        }

        public AvatarStyle.Builder setBorderColor(@ColorInt int color) {
            avatarStyle.setBorderColor(color);
            return this;
        }

        public AvatarStyle.Builder setCircleRadius(int radius) {
            avatarStyle.setCircleRadius(radius);
            return this;
        }

        public AvatarStyle.Builder setBorderWidth(int width) {
            avatarStyle.setBorderWidth(width);
            return this;
        }

        public AvatarStyle.Builder setMode(int mode) {
            avatarStyle.setMode(mode);
            return this;
        }

        public AvatarStyle.Builder setInitials(String name) {
            avatarStyle.setInitials(name);
            return this;
        }

        public AvatarStyle.Builder setTextColor(@ColorInt int color) {
            avatarStyle.setTextColor(color);
            return this;
        }

        public AvatarStyle.Builder setTextSize(int size) {
            avatarStyle.setTextSize(size);
            return this;
        }

        public AvatarStyle.Builder setGradient(Gradient gradient) {
            avatarStyle.setGradient(gradient);
            return this;
        }

        public AvatarStyle.Builder reversedGradient(boolean reversed) {
            avatarStyle.setGradientReversed(reversed);
            return this;
        }

        public AvatarStyle.Builder setImageOnly(boolean imageOnly) {
            avatarStyle.setImageOnly(imageOnly);
            return this;
        }

        public AvatarStyle.Builder setextOnTop(boolean textOnTop) {
            avatarStyle.showTextOnTop(textOnTop);
            return this;
        }

        public AvatarStyle.Builder setGradientMode(int mode) {
            avatarStyle.getGradient().setMode(mode);
            return this;
        }

        public AvatarStyle.Builder setGradientSteps(int steps) {
            avatarStyle.getGradient().setSteps(steps);
            return this;
        }

        public AvatarStyle.Builder setGradientStartColor(@ColorInt int color) {
            avatarStyle.getGradient().setStartColor(color);
            return this;
        }

        public AvatarStyle.Builder setGradientEndColor(@ColorInt int color) {
            avatarStyle.getGradient().setEndColor(color);
            return this;
        }

    }
}
