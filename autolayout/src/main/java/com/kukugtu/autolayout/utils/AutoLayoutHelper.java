package com.kukugtu.autolayout.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.kukugtu.autolayout.AutoLayoutInfo;
import com.kukugtu.autolayout.attr.HeightAttr;
import com.kukugtu.autolayout.attr.MarginAttr;
import com.kukugtu.autolayout.attr.MarginBottomAttr;
import com.kukugtu.autolayout.attr.MarginLeftAttr;
import com.kukugtu.autolayout.attr.MarginRightAttr;
import com.kukugtu.autolayout.attr.MarginTopAttr;
import com.kukugtu.autolayout.attr.MaxHeightAttr;
import com.kukugtu.autolayout.attr.MaxWidthAttr;
import com.kukugtu.autolayout.attr.MinHeightAttr;
import com.kukugtu.autolayout.attr.MinWidthAttr;
import com.kukugtu.autolayout.attr.PaddingAttr;
import com.kukugtu.autolayout.attr.PaddingBottomAttr;
import com.kukugtu.autolayout.attr.PaddingLeftAttr;
import com.kukugtu.autolayout.attr.PaddingRightAttr;
import com.kukugtu.autolayout.attr.PaddingTopAttr;
import com.kukugtu.autolayout.attr.TextSizeAttr;
import com.kukugtu.autolayout.attr.WidthAttr;
import com.kukugtu.autolayout.config.AutoLayoutConifg;

public class AutoLayoutHelper {

    private static final int[] LL = new int[]{ //
            android.R.attr.textSize,
            android.R.attr.padding,//
            android.R.attr.paddingLeft,//
            android.R.attr.paddingTop,//
            android.R.attr.paddingRight,//
            android.R.attr.paddingBottom,//
            android.R.attr.layout_width,//
            android.R.attr.layout_height,//
            android.R.attr.layout_margin,//
            android.R.attr.layout_marginLeft,//
            android.R.attr.layout_marginTop,//
            android.R.attr.layout_marginRight,//
            android.R.attr.layout_marginBottom,//
            android.R.attr.maxWidth,//
            android.R.attr.maxHeight,//
            android.R.attr.minWidth,//
            android.R.attr.minHeight,//16843072


    };

    private static final int INDEX_TEXT_SIZE = 0;
    private static final int INDEX_PADDING = 1;
    private static final int INDEX_PADDING_LEFT = 2;
    private static final int INDEX_PADDING_TOP = 3;
    private static final int INDEX_PADDING_RIGHT = 4;
    private static final int INDEX_PADDING_BOTTOM = 5;
    private static final int INDEX_WIDTH = 6;
    private static final int INDEX_HEIGHT = 7;
    private static final int INDEX_MARGIN = 8;
    private static final int INDEX_MARGIN_LEFT = 9;
    private static final int INDEX_MARGIN_TOP = 10;
    private static final int INDEX_MARGIN_RIGHT = 11;
    private static final int INDEX_MARGIN_BOTTOM = 12;
    private static final int INDEX_MAX_WIDTH = 13;
    private static final int INDEX_MAX_HEIGHT = 14;
    private static final int INDEX_MIN_WIDTH = 15;
    private static final int INDEX_MIN_HEIGHT = 16;

    public static void adjustChildren(ViewGroup mHost) {
        AutoLayoutConifg.getInstance().checkParams();

        for (int i = 0, n = mHost.getChildCount(); i < n; i++) {
            View view = mHost.getChildAt(i);
            ViewGroup.LayoutParams params = view.getLayoutParams();

            if (params instanceof AutoLayoutParams) {
                AutoLayoutInfo info =
                        ((AutoLayoutParams) params).getAutoLayoutInfo();
                if (info != null) {
                    info.fillAttrs(view);
                }
            }
        }

    }

    public static AutoLayoutInfo getAutoLayoutInfo(Context context,
                                                   AttributeSet attrs) {

        AutoLayoutInfo info = new AutoLayoutInfo();
        TypedArray array = context.obtainStyledAttributes(attrs, LL);
        int n = array.getIndexCount();


        for (int i = 0; i < n; i++) {
            int index = array.getIndex(i);

            if (!isPxVal(array.peekValue(index))) {
                continue;
            }

            int pxVal = array.getDimensionPixelOffset(index, 0);

            switch (index) {
                case INDEX_TEXT_SIZE:
                    info.addAttr(new TextSizeAttr(pxVal));
                    break;
                case INDEX_PADDING:
                    info.addAttr(new PaddingAttr(pxVal));
                    break;
                case INDEX_PADDING_LEFT:
                    info.addAttr(new PaddingLeftAttr(pxVal));
                    break;
                case INDEX_PADDING_TOP:
                    info.addAttr(new PaddingTopAttr(pxVal));
                    break;
                case INDEX_PADDING_RIGHT:
                    info.addAttr(new PaddingRightAttr(pxVal));
                    break;
                case INDEX_PADDING_BOTTOM:
                    info.addAttr(new PaddingBottomAttr(pxVal));
                    break;
                case INDEX_WIDTH:
                    info.addAttr(new WidthAttr(pxVal));
                    break;
                case INDEX_HEIGHT:
                    info.addAttr(new HeightAttr(pxVal));
                    break;
                case INDEX_MARGIN:
                    info.addAttr(new MarginAttr(pxVal));
                    break;
                case INDEX_MARGIN_LEFT:
                    info.addAttr(new MarginLeftAttr(pxVal));
                    break;
                case INDEX_MARGIN_TOP:
                    info.addAttr(new MarginTopAttr(pxVal));
                    break;
                case INDEX_MARGIN_RIGHT:
                    info.addAttr(new MarginRightAttr(pxVal));
                    break;
                case INDEX_MARGIN_BOTTOM:
                    info.addAttr(new MarginBottomAttr(pxVal));
                    break;
                case INDEX_MAX_WIDTH:
                    info.addAttr(new MaxWidthAttr(pxVal));
                    break;
                case INDEX_MAX_HEIGHT:
                    info.addAttr(new MaxHeightAttr(pxVal));
                    break;
                case INDEX_MIN_WIDTH:
                    info.addAttr(new MinWidthAttr(pxVal));
                    break;
                case INDEX_MIN_HEIGHT:
                    info.addAttr(new MinHeightAttr(pxVal));
                    break;
                default:
                    break;
            }
        }
        array.recycle();
        return info;
    }

    public static AutoLayoutInfo getAutoLayoutInfo(ViewGroup.MarginLayoutParams source) {
        AutoLayoutInfo info = new AutoLayoutInfo();
        info.addAttr(new MarginBottomAttr(source.bottomMargin));
        info.addAttr(new MarginLeftAttr(source.leftMargin));
        info.addAttr(new MarginTopAttr(source.topMargin));
        info.addAttr(new MarginRightAttr(source.rightMargin));
        return info;
    }

    private static boolean isPxVal(TypedValue val) {
        return val != null && val.type == TypedValue.TYPE_DIMENSION &&
                getComplexUnit(val.data) == TypedValue.COMPLEX_UNIT_PX;
    }

    private static int getComplexUnit(int data) {
        return TypedValue.COMPLEX_UNIT_MASK & (data);
    }



    public interface AutoLayoutParams {
        AutoLayoutInfo getAutoLayoutInfo();
    }


}
