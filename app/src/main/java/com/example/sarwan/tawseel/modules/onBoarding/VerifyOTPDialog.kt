package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseDialog
import com.example.sarwan.tawseel.entities.enums.Irrelevant
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.repository.authentication.AuthenticationRepository
import com.example.sarwan.tawseel.utils.EMPTY_STRING
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.dialog_verify_otp.*

class VerifyOTPDialog : BaseDialog<AuthenticationRepository>(R.layout.dialog_verify_otp) {

    var verificationCodeLiveData: MutableLiveData<String> = MutableLiveData()

    override fun createRepoInstance() {
        repository = getRepository(AuthenticationRepository::class.java)
    }

    private var arrayListOfPins = emptyList<Any>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arrayListOfPins = arrayListOf(pin_first, pin_second, pin_three, pin_four, pin_five, pin_six)
        viewListeners()
        dataToViews()
        setObservers()
    }

    override fun setObservers() {
        verificationCodeLiveData.observe(this, Observer {
            attachAutoVerificationCode(it)
        })
    }

    private fun attachAutoVerificationCode(code: String) {
        if (code.length == 6) {
            (pin_first as TextInputEditText).setText(code[0].toString())
            (pin_second as TextInputEditText).setText(code[1].toString())
            (pin_three as TextInputEditText).setText(code[2].toString())
            (pin_four as TextInputEditText).setText(code[3].toString())
            (pin_five as TextInputEditText).setText(code[4].toString())
            (pin_six as TextInputEditText).setText(code[5].toString())
            verifyPinNumbers()
        }
    }

    override fun dataToViews() {
        number.text = bActivity.getAppRepository().userProfile?.user?.phone
    }

    override fun viewListeners() {
        verify?.navigateOnClick {
            verifyPinNumbers()
        }

        change?.navigateOnClick {
            dismiss()
        }

        resend?.navigateOnClick {
            repository.resendCodeLiveData.value = Irrelevant.INSTANCE
        }
    }

    private fun verifyPinNumbers() {
        if (checkPinTextsAreValid()) {
            repository.verificationCodeLiveData.postValue(repository.verificationCode)
        }
    }

    private fun checkPinTextsAreValid(): Boolean {
        for (pin in arrayListOfPins) {
            if ((pin as TextInputEditText).text?.equals(EMPTY_STRING) == true) {
                pin.error = bActivity.getStringFromValues(R.string.pin_must_not_be_empty)
                return false
            } else {
                repository.verificationCode += pin.text
            }
        }
        return true
    }

    fun setAutoVerificationCode(code: String) {
        verificationCodeLiveData.value = code
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment VerifyOTPRepository.
         */

        private var instance: VerifyOTPDialog? = null

        @JvmStatic
        fun newInstance() = VerifyOTPDialog().apply {
            instance = this
            return instance!! // It can't be null at this time
        }

        @JvmStatic
        fun getInstance(): VerifyOTPDialog {
            return instance?.let { it } ?: run { newInstance() }
        }
    }
}