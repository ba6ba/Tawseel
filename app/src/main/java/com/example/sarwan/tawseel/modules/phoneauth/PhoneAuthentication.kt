package com.example.sarwan.tawseel.modules.phoneauth

import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseActivity
import com.example.sarwan.tawseel.utils.EMPTY_STRING
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class PhoneAuthentication(
    private val activity: BaseActivity<*>,
    private val callBack: PhoneAuthProviderCallBack
) {

    private var phoneNumberToAuthenticate: String =
        activity.getProfileFromSharedPreference()?.user?.phone ?: EMPTY_STRING

    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null
    private var verificationCode: String = EMPTY_STRING
    private var verificationId: String = EMPTY_STRING
    private var auth = FirebaseAuth.getInstance()
    private var phoneAuthCredential: PhoneAuthCredential? = null


    fun initiate() {
        getPhoneNumber()
        PhoneAuthProvider.getInstance()
            .verifyPhoneNumber(
                phoneNumberToAuthenticate,
                60,
                TimeUnit.SECONDS,
                activity,
                fireBasePhoneAuthProviderCallBack
            )
    }

    private fun getPhoneNumber() {
        phoneNumberToAuthenticate =
            activity.getProfileFromSharedPreference()?.user?.phone ?: EMPTY_STRING
    }

    fun requestForCodeVerification(code: String) {
        phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, code)
        phoneAuthCredential?.let {
            signInWithPhoneAuthCredential(it)
        } ?: kotlin.run {
            callBack.onVerificationStateResponse(
                PhoneAuthProviderResponse(
                    activity.getStringFromValues(R.string.signup_failed),
                    PhoneAuthProviderResponseType.ERROR
                )
            )
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    //TODO - save user credentials in preferences if required
                    callBack.onVerificationStateResponse(
                        PhoneAuthProviderResponse(
                            EMPTY_STRING,
                            PhoneAuthProviderResponseType.SUCCESS
                        )
                    )
                } else {
                    val error: String =
                        if (task.exception is FirebaseAuthInvalidCredentialsException)
                            activity.getStringFromValues(R.string.invalid_code)
                        else activity.getStringFromValues(R.string.signup_failed)

                    callBack.onVerificationStateResponse(
                        PhoneAuthProviderResponse(
                            error,
                            PhoneAuthProviderResponseType.ERROR
                        )
                    )
                }
            }
    }

    private val fireBasePhoneAuthProviderCallBack =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                callBack.onVerificationStateResponse(
                    PhoneAuthProviderResponse(
                        EMPTY_STRING,
                        PhoneAuthProviderResponseType.CODE_RECEIVED,
                        p0.smsCode
                    )
                )
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                callBack.onVerificationStateResponse(
                    PhoneAuthProviderResponse(
                        p0.localizedMessage ?: "unknown_error",
                        PhoneAuthProviderResponseType.ERROR
                    )
                )
            }

            override fun onCodeSent(
                verificationId: String,
                resendingToken: PhoneAuthProvider.ForceResendingToken
            ) {
                this@PhoneAuthentication.verificationId = verificationId
                resendToken = resendingToken
                callBack.onVerificationStateResponse(
                    PhoneAuthProviderResponse(
                        activity.getString(R.string.code_has_been_sent_message),
                        PhoneAuthProviderResponseType.CODE_REQUEST
                    )
                )
            }
        }
}




