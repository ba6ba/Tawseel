package com.example.sarwan.tawseel.modules.customer

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.repository.customer.CustomerRepository
import kotlinx.android.synthetic.main.fragment_home_customer.*


class HomeCustomerFragment : BaseFragment<CustomerRepository>(R.layout.fragment_home_customer) {

    override fun createRepoInstance() {
        repository = getRepository(CustomerRepository::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_order?.navigateOnClick {
            navigateTo(R.id.action_create_order_fragment)
        }
    }
}