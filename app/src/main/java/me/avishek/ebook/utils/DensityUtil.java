package me.avishek.ebook.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class DensityUtil {

    /**
     * Converting dp to pixel
     */
    public static int dpToPx(Context context, int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
