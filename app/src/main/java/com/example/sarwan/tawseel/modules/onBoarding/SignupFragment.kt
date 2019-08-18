package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.extensions.show
import com.example.sarwan.tawseel.interfaces.DialogInteraction
import com.example.sarwan.tawseel.repository.authentication.AuthenticationRepository
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : BaseFragment<AuthenticationRepository>(R.layout.fragment_signup), DialogInteraction {

    override fun createRepoInstance() {
        repository = getRepository(AuthenticationRepository::class.java)
    }

    override fun dismissCallBack(result: Boolean) {
        if (result){
            navigateToMainApp()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewListeners()
    }

    override fun viewListeners() {
        signup?.navigateOnClick {
            show(VerifyOTPDialog.newInstance().apply {
                dismissListener(this@SignupFragment)
            })
        }

        back?.navigateOnClick {
            navigateBack()
        }
    }
}