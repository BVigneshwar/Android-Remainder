package com.vignesh.remainder.common;

import android.content.Context;

public class CommonUtil {
    public static int convertDpToPixel(int dp, Context context){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5);
    }
}
