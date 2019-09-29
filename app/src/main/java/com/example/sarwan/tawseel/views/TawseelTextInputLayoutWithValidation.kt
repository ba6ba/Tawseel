package com.example.sarwan.tawseel.views

import android.content.Context
import android.content.res.ColorStateList
import android.text.InputType
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Patterns
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.entities.Validation
import com.example.sarwan.tawseel.entities.enums.PasswordRegex
import com.example.sarwan.tawseel.entities.enums.ValidationRule
import com.example.sarwan.tawseel.entities.enums.ValidationType
import com.example.sarwan.tawseel.extensions.hint
import com.example.sarwan.tawseel.extensions.isInRangeOf
import com.example.sarwan.tawseel.extensions.textChangeListener
import com.example.sarwan.tawseel.utils.dpToPx
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.layout_text_input_layout_with_validation.view.*
import java.util.regex.Pattern

class TawseelTextInputLayoutWithValidation @JvmOverloads constructor(
    context: Context,
    private var attrs: AttributeSet? = null,
    private var defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {

    private var mHint: String? = ""
    private var mValidationType: ValidationType = ValidationType.NO_RULE
    private var mValidationRule: ValidationRule = ValidationRule.NO_RULE
    private var mMaxLimit: Int = Int.MAX_VALUE
    private var mMinLimit: Int = 0
    private var validationRule: Int = 0
    private var minHeight: Int = 0
    private var validationError: String? = generateError()
    private var isViewEnabled : Boolean = true

    private val upperRule: Boolean by lazy {
        ValidationRule.possibleUpperRuleValues().contains(validationRule) && mValidationType == ValidationType.VALID_PASSWORD
    }
    private val lowerRule: Boolean by lazy {
        ValidationRule.possibleLowerRuleValues().contains(validationRule) && mValidationType == ValidationType.VALID_PASSWORD
    }
    private val numericRule: Boolean by lazy {
        ValidationRule.possibleNumericRuleValues().contains(validationRule) && mValidationType == ValidationType.VALID_PASSWORD
    }
    private val specialCharRule: Boolean by lazy {
        ValidationRule.possibleSpecialRuleValues().contains(validationRule) && mValidationType == ValidationType.VALID_PASSWORD
    }

    val validationResult: MutableLiveData<Validation.Result> = MutableLiveData()

    init {
        initialize()
    }

    private fun initialize() {
        View.inflate(context, R.layout.layout_text_input_layout_with_validation, this)
        getAttributes()
        updateAttributes()
    }

    private fun updateAttributes() {
//        setMinHeight()
        setIsViewEnabled()
        setHint()
        makeViewCustomised()
        setInputType()
        addTextChangeListener()
    }

    private fun setIsViewEnabled() {
        _layout?.editText?.isEnabled = isViewEnabled
    }

    private fun setMinHeight() {
        _layout?.editText?.minHeight = dpToPx(resources, minHeight)
    }

    private fun makeViewCustomised() {
        if (mValidationType == ValidationType.VALID_PASSWORD) {
            mMaxLimit = 8
            _layout?.isPasswordVisibilityToggleEnabled = true
            _layout?.setPasswordVisibilityToggleTintList(
                ColorStateList.valueOf(
                    context.resources.getColor(
                        R.color.colorPrimary
                    )
                )
            )
        } else if (mValidationType == ValidationType.PHONE) {
            _layout?.editText?.setText(context.resources.getString(R.string.plus_sign))
        }
    }

    fun setValidationType(validationType: ValidationType) {
        mValidationType = validationType
        updateAttributes()
    }

    fun setHint(hint: String? = mHint) {
        mHint = hint
        _layout?.hint(mHint ?: "")
    }

    private fun addTextChangeListener() {
        _layout?.textChangeListener {
            validateText(string = it)
        }
    }

    private fun setInputType() {
        _layout?.editText?.inputType = when (mValidationType) {
            ValidationType.VALID_PASSWORD -> {
                InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            ValidationType.VALID_EMAIL -> {
                InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }
            ValidationType.PHONE -> {
                InputType.TYPE_CLASS_PHONE
            }
            ValidationType.PRICE -> {
                InputType.TYPE_CLASS_NUMBER
            }
            else -> {
                InputType.TYPE_CLASS_TEXT
            }
        }
    }

    private fun getAttributes() {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TawseelTextInputLayoutWithValidation,
            0,
            0
        ).apply {
            try {
                mHint = getString(R.styleable.TawseelTextInputLayoutWithValidation_hint)
                mMaxLimit =
                    getInt(
                        R.styleable.TawseelTextInputLayoutWithValidation_max_number_of_characters,
                        Int.MAX_VALUE
                    )
                mMinLimit = getInt(
                    R.styleable.TawseelTextInputLayoutWithValidation_min_number_of_characters,
                    0
                )
                minHeight = getInt(
                    R.styleable.TawseelTextInputLayoutWithValidation_min_height,
                     0
                )
                mValidationType =
                    ValidationType.fromId(
                        getInt(
                            R.styleable.TawseelTextInputLayoutWithValidation_validation_type,
                            0
                        )
                    )
                validationRule =
                    getInt(R.styleable.TawseelTextInputLayoutWithValidation_validation_rule, 0)
                mValidationRule = ValidationRule.fromId(validationRule)
                isViewEnabled = getBoolean(R.styleable.TawseelTextInputLayoutWithValidation_is_view_enabled, true)
            } finally {
                recycle()
            }
        }
    }

    private fun validateText(validationType: ValidationType = mValidationType, string: String?) {
        when (validationType) {
            ValidationType.VALID_EMAIL -> {
                emailTextValidation(string)
            }
            ValidationType.VALID_PASSWORD -> {
                passwordTextValidation(string)
            }
            ValidationType.LENGTH_CONSTRAINT -> {
                lengthConstraintTextValidation(string)
            }
            ValidationType.PHONE -> {
                phoneTextValidation(string)
            }
            else -> {
                nonEmptyTextValidation(string)
            }
        }
    }

    private fun phoneTextValidation(string: String?) {
        var phoneString: String? = "+"
        if (string?.contains(phoneString.toString()) == false) {
            phoneString.plus(string)
        } else {
            phoneString = string
        }
        makeTextValidation(!TextUtils.isEmpty(phoneString), phoneString)
    }

    private fun emailTextValidation(string: String?) {
        validateEmail(string) { result ->
            makeTextValidation(result, string)
        }
    }

    private fun setValidationError(error: String? = validationError) {
        _layout.error = error
    }

    private fun validateEmail(string: String?, result: (Boolean) -> Unit) =
        result(!TextUtils.isEmpty(string) && Patterns.EMAIL_ADDRESS.matcher(string).matches())

    private fun passwordTextValidation(string: String?) {
        validatePassword(string) { result ->
            makeTextValidation(result, string)
        }
    }

    private fun validatePassword(string: String?, result: (Boolean) -> Unit) {
        var validate = !TextUtils.isEmpty(string)
        if (upperRule)
            validate = validatePasswordRegex(PasswordRegex.upper_char, string)
        if (lowerRule)
            validate = validatePasswordRegex(PasswordRegex.lower_char, string)
        if (numericRule)
            validate = validatePasswordRegex(PasswordRegex.numeric, string)
        if (specialCharRule)
            validate = validatePasswordRegex(PasswordRegex.special_char, string)
        result(validate && string?.length?.isInRangeOf(mMinLimit, mMaxLimit) == true)
    }

    private fun validatePasswordRegex(passwordRegex: PasswordRegex, string: String?) =
        Pattern.compile(passwordRegex.regex).matcher(string).matches()

    private fun lengthConstraintTextValidation(string: String?) {
        validLengthRange(string) { result ->
            makeTextValidation(result, string)
        }
    }

    private fun validLengthRange(string: String?, result: (Boolean) -> Unit) {
        result(
            !TextUtils.isEmpty(string) && string?.length?.isInRangeOf(
                mMinLimit,
                mMaxLimit
            ) == true
        )
    }

    private fun nonEmptyTextValidation(string: String?) {
        makeTextValidation(!TextUtils.isEmpty(string), string)
    }

    private fun generateError(validationType: ValidationType = mValidationType): String {
        return when (validationType) {
            ValidationType.MUST_NOT_BE_EMPTY -> {
                context.getString(R.string.must_not_be_empty)
            }

            ValidationType.LENGTH_CONSTRAINT -> {
                "${context.getString(R.string.length_constraint)} $mMaxLimit"
            }

            ValidationType.VALID_PASSWORD -> {
                "${context.getString(R.string.valid_password)} ${generatePasswordConstraintMessage()}"
            }

            ValidationType.VALID_EMAIL -> {
                context.getString(R.string.valid_email_address)
            }

            else -> {
                ""
            }
        }
    }

    private fun generatePasswordConstraintMessage(): String {
        val errorString = arrayListOf<String>()
        if (upperRule) errorString.add("upper characters")
        if (lowerRule) errorString.add("lower characters")
        if (numericRule) errorString.add("numeric characters")
        if (specialCharRule) errorString.add("special characters")
        errorString.add("length must be in between ${mMinLimit..mMaxLimit}")
        return errorString.joinToString(" , ")
    }

    private fun makeTextValidation(result: Boolean, string: String?) {
        if (result) {
            validationError = null
        } else {
            validationError = generateError()
        }
        validationResult.postValue(Validation.result(result, string))
        setValidationError()
    }
}