package com.birjuvachhani.avatarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import androidx.annotation.ColorInt;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by Birju Vachhani on 21-07-2018.
 */
public class AvatarView extends AppCompatImageView {

    private static DisplayMetrics displayMetrics;
    private Paint mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int centerX, centerY, width, height;
    private int wrappedSize;
    private Path clipPath;
    private AvatarStyle avatarStyle;
    private Rect textBounds = new Rect();

    public AvatarView(Context context) {
        super(context);
        init(context, null);
    }

    public AvatarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private static int convertDpToPixel(int dp) {
        float px = dp * ((float) displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return Math.round(px);
    }

    private void init(Context context, AttributeSet attrs) {
        avatarStyle = new AvatarStyle(context);
        displayMetrics = context.getResources().getDisplayMetrics();

        if (attrs == null) return;

        //Getting attribute values
        TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.AvatarView);

        avatarStyle.setCircleRadiusDimens(attrArray.getDimensionPixelSize(R.styleable.AvatarView_circleRadius,
                Defaults.CIRCLE_RADIUS));
        avatarStyle.setBorderWidthDimens(attrArray.getDimensionPixelSize(R.styleable.AvatarView_border_width,
                Defaults.BORDER_WIDTH));
        avatarStyle.setBorderColor(attrArray.getColor(R.styleable.AvatarView_border_color,
                Defaults.BORDER_COLOR));
        avatarStyle.setBackgroundColor(attrArray.getColor(R.styleable.AvatarView_backgroundColor, Defaults.BACKGROUND_COLOR));
        avatarStyle.setMode(attrArray.getInt(R.styleable.AvatarView_mode, Defaults.MODE));
        avatarStyle.setInitials(attrArray.getString(R.styleable.AvatarView_android_text));
        avatarStyle.setTextColor(attrArray.getColor(R.styleable.AvatarView_android_textColor, Defaults.TEXT_COLOR));
        avatarStyle.setTextSizeDimen(attrArray.getDimensionPixelSize(R.styleable.AvatarView_android_textSize, Defaults.TEXT_SIZE));

        avatarStyle.setGradient(Gradients.getGradient(context, attrArray.getInt(R.styleable.AvatarView_gradient, 1)));

        boolean isCustomGradient = attrArray.getBoolean(R.styleable.AvatarView_enableCustomGradient, false);
        if (isCustomGradient) {
            int startColor = attrArray.getColor(R.styleable.AvatarView_startColor, Color.CYAN);
            int endColor = attrArray.getColor(R.styleable.AvatarView_endColor, Color.CYAN);
            avatarStyle.setGradient(new Gradient(startColor, endColor));
        }

        avatarStyle.getGradient().setMode(attrArray.getInt(R.styleable.AvatarView_gradientMode, 1));
        avatarStyle.getGradient().setSteps(attrArray.getInt(R.styleable.AvatarView_gradientSteps, 0));
        avatarStyle.setGradientReversed(attrArray.getBoolean(R.styleable.AvatarView_reversedGradient, false));
        avatarStyle.setImageOnly(attrArray.getBoolean(R.styleable.AvatarView_imageOnly, false));
        avatarStyle.showTextOnTop(attrArray.getBoolean(R.styleable.AvatarView_textOnTop, false));

        setPaints(avatarStyle);

        //calculating view size if height and width is wrap_content
        wrappedSize = (avatarStyle.getCircleRadius() * 2) + avatarStyle.getBorderWidth() + 5;
        attrArray.recycle();
        clipPath = new Path();
    }

    private void setPaints(AvatarStyle style) {
        switch (style.getMode()) {
            case Mode.COLOR:
                mBackgroundPaint.setColor(style.getBackgroundColor());
                mBackgroundPaint.setStyle(Paint.Style.FILL);
                break;
            case Mode.GRADIENT:
                mBackgroundPaint.setShader(avatarStyle.getShader());
                mBackgroundPaint.setStyle(Paint.Style.FILL);
                break;
        }
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setColor(style.getBorderColor());
        mBorderPaint.setStrokeWidth(style.getBorderWidth());

        mTextPaint.setColor(style.getTextColor());
        mTextPaint.setTextSize(style.getTextSize());
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.getTextBounds(style.getInitials(), 0, style.getInitials().length(), textBounds);
    }

    private void setTextPaint() {
        mTextPaint.setColor(avatarStyle.getTextColor());
        mTextPaint.setTextSize(avatarStyle.getTextSize());
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.getTextBounds(avatarStyle.getInitials(), 0, avatarStyle.getInitials().length(), textBounds);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //clips view to the size of avatar
        setMeasuredDimension(wrappedSize, wrappedSize);

        //getting height and width of layout
        width = getMeasuredWidth();
        height = getMeasuredHeight();

        //getting center point of the layout
        centerX = width / 2;
        centerY = height / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //calculating bottom for letter
        int textBottom = Math.round((centerY) + (textBounds.height() / 2f));

        //checks if an image is set and user wants to display other things or not
        if (avatarStyle.isImageOnly() && getDrawable() != null) {
            clipPath.addCircle(centerX, centerY, avatarStyle.getCircleRadius() - avatarStyle.getBorderWidth() / 2f, Path.Direction.CW);
            canvas.clipPath(clipPath);

            super.onDraw(canvas);

            //drawing Initial Letter on Top
            if (avatarStyle.isTextOnTop())
                canvas.drawText(avatarStyle.getInitials(), width / 2f, textBottom, mTextPaint);
            return;
        }

        //drawing circle
        canvas.drawCircle(centerX, centerY, avatarStyle.getCircleRadius(), mBackgroundPaint);

        //drawing border
        if (avatarStyle.getBorderWidth() != 0)
            canvas.drawCircle(centerX, centerY, avatarStyle.getCircleRadius(), mBorderPaint);

        //drawing Initial Letter
        if (!avatarStyle.isTextOnTop())
            canvas.drawText(avatarStyle.getInitials(), width / 2f, textBottom, mTextPaint);

        clipPath.addCircle(centerX, centerY, avatarStyle.getCircleRadius() - avatarStyle.getBorderWidth() / 2f, Path.Direction.CW);
        canvas.clipPath(clipPath);

        super.onDraw(canvas);

        //drawing Initial Letter on Top
        if (avatarStyle.isTextOnTop())
            canvas.drawText(avatarStyle.getInitials(), width / 2f, textBottom, mTextPaint);
    }

    public AvatarStyle getAvatarStyle() {
        return avatarStyle;
    }

    public void setAvatarStyle(AvatarStyle style) {
        this.avatarStyle = style;
        wrappedSize = (avatarStyle.getCircleRadius() * 2) + avatarStyle.getBorderWidth() + 5;
        setPaints();
        invalidate();
    }

    public void setPaints() {
        setPaints(avatarStyle);
    }

    public void setBackgroundColor(@ColorInt int color) {
        avatarStyle.setBackgroundColor(color);
        mBackgroundPaint.setColor(avatarStyle.getBackgroundColor());
        invalidate();
    }

    public void setCircleRadius(int radius) {
        avatarStyle.setCircleRadius(radius);
        wrappedSize = (avatarStyle.getCircleRadius() * 2) + avatarStyle.getBorderWidth() + 5;
        requestLayout();
    }

    public void setBorderColor(@ColorInt int color) {
        avatarStyle.setBorderColor(color);
        mBorderPaint.setColor(avatarStyle.getBorderColor());
        invalidate();
    }

    public void setBorderWidth(int width) {
        avatarStyle.setBorderWidth(width);
        mBorderPaint.setStrokeWidth(avatarStyle.getBorderWidth());
        wrappedSize = (avatarStyle.getCircleRadius() * 2) + avatarStyle.getBorderWidth() + 5;
        requestLayout();
    }

    public void setInitials(String name) {
        avatarStyle.setInitials(name);
        invalidate();
    }

    public void setTextColor(@ColorInt int color) {
        avatarStyle.setTextColor(color);
        mTextPaint.setColor(avatarStyle.getTextColor());
        invalidate();
    }

    public void setTextSize(int size) {
        avatarStyle.setTextSize(size);
        mTextPaint.setTextSize(avatarStyle.getTextSize());
        invalidate();
    }

    public void setGradient(Gradient gradient) {
        avatarStyle.setGradient(gradient);
        mBackgroundPaint.setShader(avatarStyle.getShader());
        invalidate();
    }

    public void setGradient(@ColorInt int startColor, @ColorInt int endColor, int mode, int steps) {
        avatarStyle.setGradient(new Gradient(startColor, endColor, mode, steps));
        mBackgroundPaint.setShader(avatarStyle.getShader());
        invalidate();
    }

    public void setGradient(@ColorInt int startColor, @ColorInt int endColor, int mode) {
        avatarStyle.setGradient(new Gradient(startColor, endColor, mode));
        mBackgroundPaint.setShader(avatarStyle.getShader());
        invalidate();
    }

    public void setGradient(@ColorInt int startColor, @ColorInt int endColor) {
        avatarStyle.setGradient(new Gradient(startColor, endColor));
        mBackgroundPaint.setShader(avatarStyle.getShader());
        invalidate();
    }

    public void setGradientReversed(boolean reversed) {
        avatarStyle.setGradientReversed(reversed);
        mBackgroundPaint.setShader(avatarStyle.getShader());
        invalidate();
    }

    public void setMode(int mode) {
        if (mode < 1 || mode > 3) mode = 1;
        avatarStyle.setMode(mode);
        setPaints();
        invalidate();
    }

    public void showTextOnTop(boolean textOnTop) {
        avatarStyle.showTextOnTop(textOnTop);
        invalidate();
    }

    public void setImageOnly(boolean imageOnly) {
        avatarStyle.setImageOnly(imageOnly);
        invalidate();
    }

    public void setGradietMode(int mode) {
        if (mode < 1 || mode > 3) mode = 1;
        avatarStyle.getGradient().setMode(mode);
        mBackgroundPaint.setShader(avatarStyle.getShader());
        invalidate();
    }

    public void setGradientSteps(int steps) {
        avatarStyle.getGradient().setSteps(steps);
        mBackgroundPaint.setShader(avatarStyle.getShader());
        invalidate();
    }

    public static final class Mode {
        public static final int COLOR = 1;
        public static final int GRADIENT = 3;
    }

    public static final class Defaults {
        public static final int MODE = Mode.COLOR;
        public static final int CIRCLE_RADIUS = 40;
        public static final int BORDER_WIDTH = 2;
        public static final int BORDER_COLOR = Color.WHITE;
        public static final int BACKGROUND_COLOR = Color.GRAY;
        public static final int TEXT_COLOR = Color.WHITE;
        public static final int TEXT_SIZE = 16;
    }
}
