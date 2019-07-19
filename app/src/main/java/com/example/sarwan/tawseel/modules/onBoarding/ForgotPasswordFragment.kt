package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.repository.onBoarding.ForgotPasswordRepository
import kotlinx.android.synthetic.main.fragment_forgot_password.*

class ForgotPasswordFragment : BaseFragment<ForgotPasswordRepository>(R.layout.fragment_forgot_password) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewListeners()
    }

    override fun viewListeners() {
        reset?.navigateOnClick {
            getBaseActivity().showMessage("Password has been reset")
            navigateTo(R.id.action_ForgotPassword_to_LoginFragment)
        }

        back?.navigateOnClick {
            navigateBack()
        }

        register_now?.navigateOnClick {
            navigateTo(R.id.action_ForgotPassword_to_SignupFragment)
        }
    }
}