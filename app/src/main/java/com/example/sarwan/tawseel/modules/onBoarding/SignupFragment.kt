package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.User
import com.example.sarwan.tawseel.entities.enums.ProfileType
import com.example.sarwan.tawseel.entities.requests.SignupRequest
import com.example.sarwan.tawseel.entities.responses.SignupResponse
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.extensions.show
import com.example.sarwan.tawseel.modules.phoneauth.PhoneAuthProviderCallBack
import com.example.sarwan.tawseel.modules.phoneauth.PhoneAuthProviderResponse
import com.example.sarwan.tawseel.modules.phoneauth.PhoneAuthProviderResponseType
import com.example.sarwan.tawseel.modules.phoneauth.PhoneAuthentication
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.repository.authentication.AuthenticationRepository
import com.example.sarwan.tawseel.utils.EMPTY_STRING
import com.example.sarwan.tawseel.utils.getProfileTypeForApi
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_signup.back

class SignupFragment : BaseFragment<AuthenticationRepository>(R.layout.fragment_signup),
    PhoneAuthProviderCallBack {

    private var signupRequest: SignupRequest = SignupRequest()
    private var enableSignUpLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var phoneAuthentication: PhoneAuthentication
    private lateinit var otpDialog: VerifyOTPDialog

    override fun createRepoInstance() {
        repository = getRepository(AuthenticationRepository::class.java)
        phoneAuthentication = PhoneAuthentication(bActivity, this)
        otpDialog = VerifyOTPDialog.newInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewListeners()
        setObservers()
        dataToViews()
        signupRequest.userType = getProfileTypeForApi(getProfileFromSharedPreference()?.profileType)
    }

    override fun dataToViews() {
        user_name?.setHint(
            repository.getSignupNameForProfileType(
                getProfileFromSharedPreference()?.profileType ?: ProfileType.DRIVER
            )
        )
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

        enableSignUpLiveData.foreverObserver(Observer {
            signup?.isEnabled = it
        })

        repository.verificationCodeLiveData.foreverObserver(Observer {
            phoneAuthentication.requestForCodeVerification(it)
        })

        repository.resendCodeLiveData.foreverObserver(Observer {
            doPhoneAuthentication()
        })
    }

    private fun doPhoneAuthentication() {
        phoneAuthentication.initiate()
    }

    override fun callApis() {
        repository.callSignupApi(signupRequest)
    }

    private fun successApiCall(data: SignupResponse.Data?) {
        getBaseActivity().apply {
            getAppRepository().userProfile?.token = data?.token
            getAppRepository().userProfile?.user = repo.mapSignupDataToUser(data)
            getAppRepository().userProfile?.isLoggedIn = true
            saveUserProfile()
        }
        navigateToMainApp()
    }

    override fun viewListeners() {
        signup?.navigateOnClick {
            setupForPhoneAuthentication()
        }

        back?.navigateOnClick {
            navigateBack()
        }

        name_layout?.validationResult?.foreverObserver(Observer {
            enableSignUpLiveData.value = it.result
            signupRequest.name = it.text.toString()
        })

        email_layout?.validationResult?.foreverObserver(Observer {
            enableSignUpLiveData.value = it.result
            signupRequest.email = it.text.toString()
        })

        phone_layout?.validationResult?.foreverObserver(Observer {
            enableSignUpLiveData.value = it.result
            signupRequest.phone = it.text.toString()
        })

        password_layout?.validationResult?.foreverObserver(Observer {
            enableSignUpLiveData.value = it.result
            signupRequest.password = it.text.toString()
        })

        confirm_password_layout?.validationResult?.foreverObserver(Observer {
            enableSignUpLiveData.value = it.result
            signupRequest.confirmPassword = it.text.toString()
        })
    }

    private fun setupForPhoneAuthentication() {
        getBaseActivity().apply {
            getAppRepository().userProfile?.user = User(phone = signupRequest.phone)
            saveUserProfile()
        }
        doPhoneAuthentication()
    }

    private fun showOTPVerification() {
        show(otpDialog)
    }

    override fun onVerificationStateResponse(phoneAuthProviderResponse: PhoneAuthProviderResponse) {
        if (phoneAuthProviderResponse.message.isNotEmpty()) {
            showMessage(phoneAuthProviderResponse.message)
        }
        when (phoneAuthProviderResponse.type) {
            PhoneAuthProviderResponseType.SUCCESS -> {
                if (otpDialog.isAdded) {
                    otpDialog.dismiss()
                }
                callApis()
            }

            PhoneAuthProviderResponseType.CODE_REQUEST -> {
                showOTPVerification()

            }

            PhoneAuthProviderResponseType.ERROR -> {
                //
            }

            PhoneAuthProviderResponseType.CODE_RECEIVED -> {
                if (!otpDialog.isAdded) {
                    repository.alreadyVerified = true
                    callApis()
                }
                otpDialog.setAutoVerificationCode(phoneAuthProviderResponse.code ?: EMPTY_STRING)
            }
        }
    }

}