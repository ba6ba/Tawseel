package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.requests.LoginRequest
import com.example.sarwan.tawseel.entities.responses.LoginResponse
import com.example.sarwan.tawseel.extensions.hint
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.repository.authentication.AuthenticationRepository
import com.example.sarwan.tawseel.utils.GlobalData
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment<AuthenticationRepository>(R.layout.fragment_login) {

    private lateinit var loginRequest : LoginRequest

    override fun createRepoInstance() {
        repository = getRepository(AuthenticationRepository::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataToViews()
        viewListeners()
        setObservers()
    }

    override fun callApis() {
        repository.callLoginApi(loginRequest)
    }

    override fun setObservers() {
        repository.loginApiInstance.foreverObserver(Observer {apiResponse->
            when(apiResponse){

                is ApiResponse.Error -> {
                    errorApiCall(apiResponse.message)
                }

                is ApiResponse.Success -> {
                    successApiCall(apiResponse.data?.data)
                }
            }
        })
    }

    private fun successApiCall(data: LoginResponse.Data?) {
        getBaseActivity().apply {
            getAppRepository().userProfile?.token = data?.token
            getAppRepository().userProfile?.user = data?.user
            saveUserProfile()
            navigateToMainApp()
        }
    }

    override fun dataToViews() {
        user_name_layout?.hint(getBaseActivity().getString(repository.getStringForAuthenticationType()))
    }

    override fun viewListeners() {
        login?.navigateOnClick {
            callApis()
        }

        back?.navigateOnClick {
            navigateBack()
        }

        forget_password?.navigateOnClick {
            navigateTo(R.id.action_LoginFragment_to_ForgotPasswordFragment)
        }
    }
}