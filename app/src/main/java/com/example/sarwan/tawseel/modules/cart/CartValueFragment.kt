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
import com.example.sarwan.tawseel.repository.customer.CartRepository
import kotlinx.android.synthetic.main.layout_cart_value.*

class CartValueFragment : BaseFragment<CartRepository>(R.layout.layout_cart_value) {

    override val repository: CartRepository = getRepository(CartRepository::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewListeners()
        setObservers()
    }

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
            cartItemListener.value?.decBy()
            added = false
        }
    }

    private fun add() {
        repository.apply {
            cartItemListener.value?.incBy()
            added = true
        }
    }
}