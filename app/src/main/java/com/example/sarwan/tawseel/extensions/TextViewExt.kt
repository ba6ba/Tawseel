package com.example.sarwan.tawseel.extensions

import android.widget.TextView

fun TextView.applyText(string : String, hideIfEmpty : Boolean = string.isEmpty()) {
    if (hideIfEmpty) visible(boolean = false) else text = string
}