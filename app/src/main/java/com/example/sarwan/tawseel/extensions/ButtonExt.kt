package com.example.sarwan.tawseel.extensions

import android.widget.Button
import com.example.sarwan.tawseel.R

fun Button.booleanText(boolean: Boolean, trueText: String, falseText: String) {
    text = if (boolean) trueText else falseText
}

fun Boolean.onTrue(action: (Boolean) -> Unit)  {
    if (this) action(this)
}

fun Boolean.bool(action: (Boolean) -> Unit) = action(this)

fun Button.isEnabled(boolean: Boolean) {
    background = if (boolean) resources.getDrawable(R.drawable.igloo_shape) else resources.getDrawable(R.drawable.igloo_shape_disabled)
    isEnabled = boolean
}