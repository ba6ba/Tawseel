package com.example.sarwan.tawseel.modules.notifications

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.repository.customer.CustomerRepository

class NotificationsFragment : BaseFragment<CustomerRepository>(R.layout.fragment_notifications) {

    override val repository: CustomerRepository = getRepository(CustomerRepository::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}