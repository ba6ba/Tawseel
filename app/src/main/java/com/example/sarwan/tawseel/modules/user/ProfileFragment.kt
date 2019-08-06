package com.example.sarwan.tawseel.modules.user

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.repository.common.ProfileRepository
import com.example.sarwan.tawseel.repository.customer.CustomerRepository

class ProfileFragment : BaseFragment<ProfileRepository>(R.layout.fragment_profile) {

    override val repository: ProfileRepository = getRepository(ProfileRepository::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}