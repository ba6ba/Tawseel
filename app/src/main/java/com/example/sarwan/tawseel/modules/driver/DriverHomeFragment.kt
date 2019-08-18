package com.example.sarwan.tawseel.modules.driver

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.repository.business.BusinessRepository
import com.example.sarwan.tawseel.repository.driver.DriverRepository
import kotlinx.android.synthetic.main.fragment_home_driver.*

class DriverHomeFragment : BaseFragment<DriverRepository>(R.layout.fragment_home_driver) {

    override fun createRepoInstance() {
        repository = getRepository(DriverRepository::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewListeners()
    }

    override fun viewListeners() {
        get_orders?.setOnCheckedChangeListener { compoundButton, b ->
            repository.getOrderLiveData().postValue(b)
        }

        repository.getOrderLiveData().observe(this, Observer {
            canReceiveOrders(it)
        })
    }

    private fun canReceiveOrders(canReceive: Boolean?) {
        if (canReceive == true){
            repository.apply {
                requestForOrder {
                    navigateTo(R.id.driverHomeFragment_to_driverNewOrderFragment)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        repository.getOrderLiveData().removeObservers(this@DriverHomeFragment)
    }
}