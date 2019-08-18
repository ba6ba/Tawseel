package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.enums.Profile
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
            repository.profile = Profile.CUSTOMER
            navigateTo(R.id.action_splashFragment_to_OnBoardingFragment, bundle = getRepository(AuthenticationRepository::class.java).getCustomerBundle())
        }

        company?.navigateOnClick {
            repository.profile = Profile.BUSINESS
            navigateTo(R.id.action_splashFragment_to_OnBoardingFragment, bundle = getRepository(AuthenticationRepository::class.java).getCompanyBundle())
        }

        delivery?.navigateOnClick {
            repository.profile = Profile.DRIVER
            navigateTo(R.id.action_splashFragment_to_OnBoardingFragment, bundle = getRepository(AuthenticationRepository::class.java).getDriverBundle())
        }
    }
}
