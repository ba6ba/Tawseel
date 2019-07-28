package com.example.sarwan.tawseel.extensions

import android.widget.Button

fun Button.booleanText(boolean: Boolean, trueText : String , falseText : String) {
    text = if (boolean) trueText else falseText
}