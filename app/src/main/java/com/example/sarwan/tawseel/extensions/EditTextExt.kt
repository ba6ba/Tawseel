package com.example.sarwan.tawseel.extensions

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.hint(string: String) {
    editText?.hint = string
}