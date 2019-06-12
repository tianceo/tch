package com.boe.cn.themapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * created by  Tigers
 * at 2019/5/7
 *就是写一个View, 支持paddingTop= 状态栏高度值 的View,
 * 解释下两个类型:
 * use_height: 设置当前布局高度=状态栏高度值 用于无子View时的占位
 * use_padding_top: 设置当前顶部padding=状态栏高度值 用于有子View时的占位
 * 适配低于4.4时 占位View的高度为0 所以不可见
 */
public class StatusBarHeightView extends LinearLayout {
    private int statusBarHeight;
    private int type;

    public StatusBarHeightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StatusBarHeightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public StatusBarHeightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (resourceId > 0) {
                statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            }
        } else {
            //低版本 直接设置0
            statusBarHeight = 0;
        }
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.StatusBarHeightView);
            type = typedArray.getInt(R.styleable.StatusBarHeightView_use_type, 0);
            typedArray.recycle();
        }
        if (type == 1) {
            setPadding(getPaddingLeft(), statusBarHeight, getPaddingRight(), getPaddingBottom());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (type == 0) {
            setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), statusBarHeight);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }


}
