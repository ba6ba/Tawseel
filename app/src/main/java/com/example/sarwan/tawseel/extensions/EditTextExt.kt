package com.example.sarwan.tawseel.extensions

import android.content.Context
import android.content.SharedPreferences
import com.example.sarwan.tawseel.utils.Global.PREFS_NAME
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.hint(string: String) {
    hint = string
}