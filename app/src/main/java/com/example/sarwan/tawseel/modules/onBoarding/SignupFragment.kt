package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.repository.onBoarding.SignupRepository
import kotlinx.android.synthetic.main.fragment_on_boarding.*
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : BaseFragment<SignupRepository>(R.layout.fragment_signup) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewListeners()
    }

    override fun viewListeners() {
        sign_up?.navigateOnClick {
            navigateTo(R.id.action_signupFragment_to_MainActivity)
        }

        back?.navigateOnClick {
            navigateBack()
        }
    }
}