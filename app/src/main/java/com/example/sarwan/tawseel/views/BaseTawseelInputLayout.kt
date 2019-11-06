package com.example.sarwan.tawseel.views

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.entities.Validation
import com.google.android.material.textfield.TextInputLayout

open class BaseTawseelInputLayout @JvmOverloads constructor(
    context: Context,
    private var attrs: AttributeSet? = null,
    private var defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {

    protected var mHint: String? = ""
    protected var isViewEnabled: Boolean = true
    val validationResult: MutableLiveData<Validation.Result> = MutableLiveData()

    init {
        validationResult.value = Validation.result(result = false)
    }

}