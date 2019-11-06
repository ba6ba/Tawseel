package com.example.sarwan.tawseel.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.AutoCompleteTextView
import android.widget.EditText
import com.example.sarwan.tawseel.entities.enums.Irrelevant
import com.example.sarwan.tawseel.entities.enums.ValidationRule
import com.example.sarwan.tawseel.entities.enums.ValidationType
import com.example.sarwan.tawseel.helper.TawseelTextWatcher
import com.example.sarwan.tawseel.utils.EMPTY_STRING
import com.example.sarwan.tawseel.views.BaseTawseelInputLayout
import com.example.sarwan.tawseel.views.TawseelTextInputLayoutWithValidation
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

fun TextInputLayout.textChangeListener(threshold : Int, any: (Any?) -> Unit) {
    editText?.addTextChangedListener(object : TawseelTextWatcher(){
        override fun onTextChange(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            (p3 >= threshold).bool {
                if (it) any(charSequence?.toString()) else any(Irrelevant.INSTANCE)
            }
        }
    })
}


fun allFieldsAreValid(vararg editText: BaseTawseelInputLayout) = editText.all { it.validationResult.value?.result == true}
