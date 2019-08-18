package com.example.sarwan.tawseel.modules.cart

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.actionOnClick
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.extensions.decBy
import com.example.sarwan.tawseel.extensions.incBy
import com.example.sarwan.tawseel.repository.customer.CustomerRepository
import kotlinx.android.synthetic.main.layout_cart_value.*

class CartValueFragment : BaseFragment<CustomerRepository>(R.layout.layout_cart_value) {

    override fun activityCreated(savedInstanceState: Bundle?) {
        repository.cartItemListener.value = 0
    }

    override fun createRepoInstance() {
        repository = getRepository(CustomerRepository::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewListeners()
        setObservers()
        setCartValue(getItemCountFromCartIfAdded())
    }

    private fun getItemCountFromCartIfAdded(): Int = repository.getList().filter { it.id == repository.getData().id }.count()

    override fun setObservers() {
        repository.cartItemListener.foreverObserver(Observer { count->
            setCartValue(count)
        })
    }

    private fun setCartValue(count: Int) {
        value?.applyText(count.toString())
    }

    override fun viewListeners() {
        minus?.actionOnClick {
            remove()
        }

        plus?.actionOnClick {
            add()
        }
    }

    private fun remove() {
        repository.apply {
            cartItemListener.value = cartItemListener.value?.decBy()
            added = false
        }
    }

    private fun add() {
        repository.apply {
            cartItemListener.value = cartItemListener.value?.incBy()
            added = true
        }
    }
}