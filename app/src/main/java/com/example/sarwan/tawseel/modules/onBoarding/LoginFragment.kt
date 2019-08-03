package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.hint
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.repository.onBoarding.AuthenticationRepository
import com.example.sarwan.tawseel.utils.GlobalData
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment<AuthenticationRepository>(R.layout.fragment_login) {

    override fun bundleOnCreated(bundle: Bundle?) {
        getRepository(AuthenticationRepository::class.java).userName(bundle?.getInt(GlobalData.PARAM))

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataToViews()
        viewListeners()
    }

    override fun dataToViews() {
        user_name_layout?.hint(getBaseActivity().getString(getRepository(AuthenticationRepository::class.java).userNameType))
    }

    override fun viewListeners() {
        login?.navigateOnClick {
            navigateTo(getRepository(AuthenticationRepository::class.java).getActivityId())
        }

        back?.navigateOnClick {
            navigateBack()
        }

        forget_password?.navigateOnClick {
            navigateTo(R.id.action_LoginFragment_to_ForgotPasswordFragment)
        }
    }
}