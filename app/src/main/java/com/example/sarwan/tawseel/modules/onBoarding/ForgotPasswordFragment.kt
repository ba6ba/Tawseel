package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.requests.ForgotPasswordRequest
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.repository.authentication.AuthenticationRepository
import kotlinx.android.synthetic.main.fragment_forgot_password.*

class ForgotPasswordFragment :
    BaseFragment<AuthenticationRepository>(R.layout.fragment_forgot_password) {

    private var forgotPasswordRequest: ForgotPasswordRequest = ForgotPasswordRequest()

    override fun createRepoInstance() {
        repository = getRepository(AuthenticationRepository::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewListeners()
        setObservers()
    }

    override fun viewListeners() {
        reset?.navigateOnClick {
            repository.callForgotPasswordApi(forgotPasswordRequest)
        }

        back?.navigateOnClick {
            navigateBack()
        }

        register_now?.navigateOnClick {
            navigateTo(R.id.action_ForgotPassword_to_SignupFragment)
        }

        email_layout?.validationResult?.foreverObserver(Observer {
            reset.isEnabled = it.result
            forgotPasswordRequest.email = it.text.toString()
        })
    }

    override fun setObservers() {
        repository.forgotPasswordApiInstance.foreverObserver(Observer {
            when (it) {
                is ApiResponse.Success -> {
                    getBaseActivity().showMessage("New password send to email")
                    navigateTo(R.id.action_ForgotPassword_to_LoginFragment)
                }

                is ApiResponse.Error -> {
                    getBaseActivity().showMessage(it.message)
                }
            }
        })
    }
}