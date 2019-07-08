package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.repository.OnBoardingRepository
import kotlinx.android.synthetic.main.fragment_on_boarding.*

class OnBoardingFragment : BaseFragment<OnBoardingRepository>(R.layout.fragment_on_boarding) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewListeners()
    }

    override fun viewListeners() {
        sign_up?.navigateOnClick{
            navigateTo(R.id.action_onBoardingFragment_to_SignupFragment)
        }

        login_with_email?.navigateOnClick {
            navigateTo(R.id.action_onBoardingFragment_to_LoginFragment, bundle = getRepository(OnBoardingRepository::class.java).emailBundle)
        }

        login_with_phone?.navigateOnClick {
            navigateTo(R.id.action_onBoardingFragment_to_LoginFragment, bundle = getRepository(OnBoardingRepository::class.java).phoneBundle)
        }

        skip?.navigateOnClick {
            navigateTo(R.id.action_onBoardingFragment_to_MainActivity)
        }
    }
}