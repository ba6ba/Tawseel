package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.enums.AuthenticationType
import com.example.sarwan.tawseel.entities.requests.LoginRequest
import com.example.sarwan.tawseel.entities.responses.LoginResponse
import com.example.sarwan.tawseel.entities.responses.StoreResponse
import com.example.sarwan.tawseel.extensions.hint
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.repository.authentication.AuthenticationRepository
import com.example.sarwan.tawseel.utils.GlobalData
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment<AuthenticationRepository>(R.layout.fragment_login) {

    private var loginRequest = LoginRequest()
    private var enableLoginLiveData: MutableLiveData<Boolean> = MutableLiveData()

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
        repository.loginApiInstance.observe(viewLifecycleOwner, Observer { apiResponse ->
            when (apiResponse) {

                is ApiResponse.Error -> {
                    errorApiCall(apiResponse.message)
                }

                is ApiResponse.Success -> {
                    successApiCall(apiResponse.data?.data)
                }
            }
        })

        enableLoginLiveData.foreverObserver(Observer {
            login?.isEnabled = it
        })
    }

    private fun successApiCall(data: LoginResponse.Data?) {
        getBaseActivity().apply {
            getAppRepository().userProfile?.token = data?.token
            getAppRepository().userProfile?.user = data?.user
            getAppRepository().userProfile?.isLoggedIn = true
            getAppRepository().userProfile?.business = data?.store
            saveUserProfile()
            navigateToMainApp()
        }
    }

    override fun dataToViews() {
        user_name?.setValidationType(repository.getValidationTypeForAuthenticationType())
        user_name?.setHint(getBaseActivity().getString(repository.getStringForAuthenticationType()))
        loginRequest.loginType = repository.getLoginTypeForAuthenticationType()
    }

    override fun viewListeners() {
        login?.navigateOnClick {
            callApis()
        }

        user_name?.validationResult?.foreverObserver(Observer {
            enableLoginLiveData.value = it.result
            setUserName(it.text.toString())
        })

        password?.validationResult?.foreverObserver(Observer {
            enableLoginLiveData.value = it.result
            loginRequest.password = it.text.toString()
        })

        back?.navigateOnClick {
            navigateBack()
        }

        forget_password?.navigateOnClick {
            navigateTo(R.id.action_LoginFragment_to_ForgotPasswordFragment)
        }
    }

    private fun setUserName(text: String) {
        if (repository.authenticationType == AuthenticationType.PHONE)
            loginRequest.phone = text
        else {
            loginRequest.email = text
        }
    }
}

