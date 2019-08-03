package com.example.sarwan.tawseel.extensions

import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.DimenRes

fun Resources.dpToInt(@DimenRes resId : Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, getDimension(resId), displayMetrics).toInt()