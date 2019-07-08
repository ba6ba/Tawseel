package com.example.sarwan.tawseel.modules.onBoarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.repository.SplashRepository
import com.example.sarwan.tawseel.utils.navigate
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : BaseFragment<SplashRepository>(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRepository(SplashRepository::class.java).navigateIf(success = {
            navigateTo(R.id.action_splashFragment_to_MainActivity, withDelay = true)
        }, failure = {
            checkForClickAction()
        })
    }

    private fun checkForClickAction() {
        customer?.navigateOnClick {
            navigateTo(R.id.action_splashFragment_to_OnBoardingFragment, bundle = getRepository(SplashRepository::class.java).customerBundle)
        }

        company?.navigateOnClick {
            navigateTo(R.id.action_splashFragment_to_OnBoardingFragment, bundle = getRepository(SplashRepository::class.java).companyBundle)
        }

        delivery?.navigateOnClick {
            navigateTo(R.id.action_splashFragment_to_OnBoardingFragment, bundle = getRepository(SplashRepository::class.java).deliveryBundle)
        }
    }
}
