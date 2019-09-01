package com.example.sarwan.tawseel.extensions

import android.text.Editable
import android.text.TextWatcher
import com.example.sarwan.tawseel.entities.enums.ValidationRule
import com.example.sarwan.tawseel.entities.enums.ValidationType
import com.example.sarwan.tawseel.helper.TawseelTextWatcher
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.hint(string: String) {
    hint = string
}

fun TextInputLayout.textChangeListener(string: (String?) -> Unit) {
    editText?.addTextChangedListener(object : TawseelTextWatcher(){
        override fun onTextChange(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            string(charSequence?.toString())
        }
    })
}
