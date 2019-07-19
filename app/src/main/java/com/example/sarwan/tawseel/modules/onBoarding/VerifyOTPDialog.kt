package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseDialog
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.repository.onBoarding.VerifyOTPRepository
import kotlinx.android.synthetic.main.dialog_verify_otp.*

class VerifyOTPDialog : BaseDialog<VerifyOTPRepository>(R.layout.dialog_verify_otp) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewListeners()
    }

    override fun viewListeners() {
        verify?.navigateOnClick {
            action = true
            dismiss()
        }

        back?.navigateOnClick {
            dismiss()
        }

        change?.navigateOnClick {
            dismiss()
        }

        resend?.navigateOnClick {
            dismiss()
        }
    }


    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment VerifyOTPRepository.
         */

        private var instance : VerifyOTPDialog ? = null
        @JvmStatic
        fun newInstance() = VerifyOTPDialog().apply {
            instance = this
            return instance!! // It can't be null at this time
        }

        @JvmStatic
        fun getInstance() : VerifyOTPDialog {
            return instance?.let { it }?:run { newInstance() }
        }
    }
}