package com.example.sarwan.tawseel.modules.user

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.repository.customer.CustomerRepository

class UserFragment : BaseFragment<CustomerRepository>(R.layout.fragment_user) {

    override val repository: CustomerRepository = getRepository(CustomerRepository::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}