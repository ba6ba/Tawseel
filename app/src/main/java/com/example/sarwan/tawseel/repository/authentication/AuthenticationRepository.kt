package com.example.sarwan.tawseel.repository.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseActivity
import com.example.sarwan.tawseel.entities.User
import com.example.sarwan.tawseel.entities.enums.AuthenticationType
import com.example.sarwan.tawseel.entities.enums.Irrelevant
import com.example.sarwan.tawseel.entities.enums.ProfileType
import com.example.sarwan.tawseel.entities.enums.ValidationType
import com.example.sarwan.tawseel.entities.requests.ForgotPasswordRequest
import com.example.sarwan.tawseel.entities.requests.LoginRequest
import com.example.sarwan.tawseel.entities.requests.SignupRequest
import com.example.sarwan.tawseel.entities.responses.GeneralResponse
import com.example.sarwan.tawseel.entities.responses.LoginResponse
import com.example.sarwan.tawseel.entities.responses.SignupResponse
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.network.NetworkRepository
import com.example.sarwan.tawseel.network.RetrofitCustomResponse
import com.example.sarwan.tawseel.repository.BaseRepository

class AuthenticationRepository : BaseRepository() {

    var alreadyVerified: Boolean = false
    var verificationCode: String = ""
    var verificationCodeLiveData: MutableLiveData<String> = MutableLiveData()
    var resendCodeLiveData: MutableLiveData<Irrelevant> = MutableLiveData()
    var authenticationType: AuthenticationType = AuthenticationType.EMAIL
    private var _loginApiInstance: MediatorLiveData<ApiResponse<LoginResponse>> = MediatorLiveData()
    var loginApiInstance: MutableLiveData<ApiResponse<LoginResponse>> = _loginApiInstance
    private var _signupApiInstance: MediatorLiveData<ApiResponse<SignupResponse>> =
        MediatorLiveData()
    var signupApiInstance: MutableLiveData<ApiResponse<SignupResponse>> = _signupApiInstance
    private var _forgotPasswordApiInstance: MediatorLiveData<ApiResponse<GeneralResponse>> =
        MediatorLiveData()
    var forgotPasswordApiInstance: MutableLiveData<ApiResponse<GeneralResponse>> = _forgotPasswordApiInstance

    private fun isLoggedIn(activity: BaseActivity<*>) =
        activity.getProfileFromSharedPreference()?.isLoggedIn

    fun navigateIf(activity: BaseActivity<*>, success: () -> Unit, failure: () -> Unit) =
        if (isLoggedIn(activity) == true) success() else failure()

    fun getStringForAuthenticationType(type: AuthenticationType = authenticationType) =
        if (type == AuthenticationType.EMAIL) R.string.email else R.string.phone

    fun getValidationTypeForAuthenticationType(type: AuthenticationType = authenticationType) =
        if (type == AuthenticationType.EMAIL) ValidationType.VALID_EMAIL else ValidationType.PHONE

    fun getLoginTypeForAuthenticationType(type: AuthenticationType = authenticationType) =
        if (type == AuthenticationType.EMAIL) "email" else "phone"

    fun getSignupNameForProfileType(type: ProfileType) =
        if (type == ProfileType.BUSINESS) "Business Name" else "Full Name"

    fun mapSignupDataToUser(signupResponse: SignupResponse.Data?) = User().apply {
        _id = signupResponse?._id
        email = signupResponse?.email
        userType = signupResponse?.userType
        phone = signupResponse?.phone
        name = signupResponse?.name
    }

    fun callLoginApi(params: LoginRequest, activity: BaseActivity<*>) {
        _loginApiInstance.addSource(loginApi(params, activity)) {
            _loginApiInstance.value = it
        }
    }

    private fun loginApi(
        params: LoginRequest,
        activity: BaseActivity<*>
    ): LiveData<ApiResponse<LoginResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<LoginResponse>> = MutableLiveData()
        NetworkRepository.getInstance().login(params)
            .enqueue(object : RetrofitCustomResponse<LoginResponse>(responseLiveData, activity) {})
        return responseLiveData
    }

    fun callSignupApi(params: SignupRequest, activity: BaseActivity<*>) {
        _signupApiInstance.addSource(signupApi(params, activity)) {
            _signupApiInstance.value = it
        }
    }

    private fun signupApi(
        params: SignupRequest,
        activity: BaseActivity<*>
    ): LiveData<ApiResponse<SignupResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<SignupResponse>> = MutableLiveData()
        NetworkRepository.getInstance().signup(params)
            .enqueue(object : RetrofitCustomResponse<SignupResponse>(responseLiveData, activity){})
        return responseLiveData
    }

    fun callForgotPasswordApi(params: ForgotPasswordRequest, activity: BaseActivity<*>) {
        _forgotPasswordApiInstance.addSource(forgotPasswordApi(params, activity)) {
            _forgotPasswordApiInstance.value = it
        }
    }

    private fun forgotPasswordApi(
        params: ForgotPasswordRequest,
        activity: BaseActivity<*>
    ): LiveData<ApiResponse<GeneralResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<GeneralResponse>> = MutableLiveData()
        NetworkRepository.getInstance().forgotPassword(params)
            .enqueue(object : RetrofitCustomResponse<GeneralResponse>(responseLiveData, activity){})
        return responseLiveData
    }
}