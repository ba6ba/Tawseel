package com.example.sarwan.tawseel.modules.driver

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.repository.driver.DriverOrderStatusRepository

class DriverOrderStatusFragment : BaseFragment<DriverOrderStatusRepository>(R.layout.fragment_driver_status) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        @JvmStatic
        fun newInstance() = DriverOrderStatusFragment()
    }
}