package com.example.sarwan.tawseel.modules.cart

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.actionOnClick
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.repository.customer.CustomerRepository
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : BaseFragment<CustomerRepository>(R.layout.fragment_cart) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewListeners()
        dataToViews()
    }

    override fun dataToViews() {
        setCartValue()
    }

    private fun setCartValue() {
        value?.applyText(getRepository(CustomerRepository::class.java).getCartValue().toString())
    }

    override fun viewListeners() {
        minus?.actionOnClick {
            removeItemFromCart()
            setCartValue()
        }

        plus?.actionOnClick {
            addItemInCart()
            setCartValue()
        }
    }

    private fun removeItemFromCart() {
        getRepository(CustomerRepository::class.java).removeFromCart()
    }

    private fun addItemInCart() {
        getRepository(CustomerRepository::class.java).addInCart()
    }
}