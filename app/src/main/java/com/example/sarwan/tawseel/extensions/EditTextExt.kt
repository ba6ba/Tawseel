package com.example.sarwan.tawseel.extensions

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.hint(string: String) {
    hint = string
}

fun TextInputLayout.textChangeListener(string : (String?) -> Unit) {
    editText?.addTextChangedListener(object : TextWatcher{
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            string(p0?.toString())
        }
    })
}