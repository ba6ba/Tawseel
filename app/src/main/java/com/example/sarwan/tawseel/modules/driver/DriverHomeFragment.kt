package com.example.sarwan.tawseel.modules.driver

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.repository.driver.HomeRepository
import kotlinx.android.synthetic.main.fragment_home_driver.*

class DriverHomeFragment : BaseFragment<HomeRepository>(R.layout.fragment_home_driver) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewListeners()
    }

    override fun viewListeners() {
        get_orders?.setOnCheckedChangeListener { compoundButton, b ->
            getRepository(HomeRepository::class.java).getOrderLiveData().postValue(b)
        }

        getRepository(HomeRepository::class.java).getOrderLiveData().observe(this, Observer {
            canReceiveOrders(it)
        })
    }

    private fun canReceiveOrders(canReceive: Boolean?) {
        if (canReceive == true){
            get_orders.isEnabled = false
            getRepository(HomeRepository::class.java).requestForOrder {
                navigateTo(R.id.driverHomeFragment_to_driverNewOrderFragment)
            }
        }
    }
}