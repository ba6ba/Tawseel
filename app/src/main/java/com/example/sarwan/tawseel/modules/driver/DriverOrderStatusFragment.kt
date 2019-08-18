package com.example.sarwan.tawseel.modules.driver

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.repository.driver.DriverRepository

class DriverOrderStatusFragment : BaseFragment<DriverRepository>(R.layout.fragment_driver_status) {

    override fun createRepoInstance() {
        repository = getRepository(DriverRepository::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        @JvmStatic
        fun newInstance() = DriverOrderStatusFragment()
    }
}