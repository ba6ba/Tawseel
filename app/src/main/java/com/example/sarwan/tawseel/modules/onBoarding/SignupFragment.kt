package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.requests.SignupRequest
import com.example.sarwan.tawseel.entities.responses.SignupResponse
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.extensions.show
import com.example.sarwan.tawseel.interfaces.DialogInteraction
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.repository.authentication.AuthenticationRepository
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : BaseFragment<AuthenticationRepository>(R.layout.fragment_signup), DialogInteraction {

    private lateinit var signupRequest: SignupRequest

    override fun createRepoInstance() {
        repository = getRepository(AuthenticationRepository::class.java)
    }

    override fun dismissCallBack(result: Boolean) {
        if (result) {
            navigateToMainApp()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewListeners()
        setObservers()
    }

    override fun setObservers() {
        repository.signupApiInstance.foreverObserver(Observer { apiResponse ->
            when (apiResponse) {
                is ApiResponse.Success -> {
                    successApiCall(apiResponse.data?.data)
                }

                is ApiResponse.Error -> {
                    errorApiCall(apiResponse.message)
                }
            }
        })
    }

    override fun callApis() {
        repository.callSignupApi(signupRequest)
    }

    private fun successApiCall(data: SignupResponse.Data?) {
        getBaseActivity().apply {
            getAppRepository().userProfile?.token = data?.token
            getAppRepository().userProfile?.user?.copy(
                _id = data?._id ?: "",
                name = data?.name ?: "",
                phone = data?.phone ?: "",
                email = data?.email ?: "",
                userType = data?.userType ?: ""
            )
            saveUserProfile()
        }
        showOTPVerification()
    }

    override fun viewListeners() {
        signup?.navigateOnClick {
            callApis()
        }

        back?.navigateOnClick {
            navigateBack()
        }
    }

    private fun showOTPVerification() {
        show(VerifyOTPDialog.newInstance().apply {
            dismissListener(this@SignupFragment)
        })
    }
}