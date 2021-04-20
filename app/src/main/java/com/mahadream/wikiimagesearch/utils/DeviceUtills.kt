package com.mahadream.wikiimagesearch.utils

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics

object DeviceUtills {
    fun getDevideWidth(): Int {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    fun convertPixelsToDp(px: Long, context: Context): Int {
        return ((px / (context.getResources()
            .getDisplayMetrics().densityDpi  / DisplayMetrics.DENSITY_DEFAULT)).toInt())
    }
}