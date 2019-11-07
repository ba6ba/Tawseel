package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.repository.authentication.AuthenticationRepository
import kotlinx.android.synthetic.main.fragment_driver_document_screen.*

class DriverDocumentScreen : BaseFragment<AuthenticationRepository>(R.layout.fragment_driver_document_screen) {

    override fun createRepoInstance() {
        repository = getRepository(AuthenticationRepository::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ok?.navigateOnClick {
            navigateTo(R.id.action_DriverDocument_to_LoginFragment)
        }
    }
}
