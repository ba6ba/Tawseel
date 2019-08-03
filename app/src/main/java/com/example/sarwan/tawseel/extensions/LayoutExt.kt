package com.example.sarwan.tawseel.extensions

import android.widget.LinearLayout

fun LinearLayout.LayoutParams.setMargin(marginLeft : Int = 0, marginTop : Int = 0, marginRight : Int = 0, marginBottom : Int = 0) {
    setMargins(marginLeft,marginTop,marginRight,marginBottom)
}

fun LinearLayout.layoutParams(width : Int = LinearLayout.LayoutParams.MATCH_PARENT, height : Int = LinearLayout.LayoutParams.MATCH_PARENT)
        = LinearLayout.LayoutParams(width, height)