package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.enums.ProfileType
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.repository.authentication.AuthenticationRepository
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : BaseFragment<AuthenticationRepository>(R.layout.fragment_splash) {

    override fun createRepoInstance() {
        repository = getRepository(AuthenticationRepository::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository.navigateIf(success = {
            navigateTo(R.id.action_splashFragment_to_MainActivity, withDelay = true)
        }, failure = {
            checkForClickAction()
        })
    }

    private fun checkForClickAction() {
        customer?.navigateOnClick {
            getBaseActivity().getAppRepository().userProfile?.profileType = ProfileType.CUSTOMER
            navigateTo(R.id.action_splashFragment_to_OnBoardingFragment)
        }

        company?.navigateOnClick {
            getBaseActivity().getAppRepository().userProfile?.profileType = ProfileType.BUSINESS
            navigateTo(R.id.action_splashFragment_to_OnBoardingFragment)
        }

        delivery?.navigateOnClick {
            getBaseActivity().getAppRepository().userProfile?.profileType = ProfileType.DRIVER
            navigateTo(R.id.action_splashFragment_to_OnBoardingFragment)
        }
    }
}
