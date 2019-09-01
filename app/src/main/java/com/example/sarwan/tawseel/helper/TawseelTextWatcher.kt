package com.example.sarwan.tawseel.helper

import android.text.Editable
import android.text.TextWatcher

abstract class TawseelTextWatcher : TextWatcher {
    abstract fun onTextChange(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int)
    override fun afterTextChanged(p0: Editable?) {}

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        onTextChange(charSequence = p0, p1 = p1, p2 = p2, p3 = p3)
    }
}