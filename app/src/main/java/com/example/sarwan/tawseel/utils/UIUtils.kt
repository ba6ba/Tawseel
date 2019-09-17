package com.example.sarwan.tawseel.utils

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.roundToInt

fun dpToPx(resources: Resources, value: Int) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, value.toFloat(), resources.displayMetrics
).roundToInt()