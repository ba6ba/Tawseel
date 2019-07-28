package com.example.sarwan.tawseel.extensions

import android.widget.TextView

fun TextView.applyText(string : String?, hideIfEmpty : Boolean = string?.isEmpty() == true) {
    if (hideIfEmpty) visible(boolean = false) else text = string
}

fun TextView.applyText(stringList : ArrayList<String>?, hideIfEmpty : Boolean = stringList?.isEmpty() == true) {
    if (hideIfEmpty) visible(boolean = false) else text = stringList?.joinToString(separator = ",")
}