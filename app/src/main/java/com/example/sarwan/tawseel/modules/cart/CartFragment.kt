package com.example.sarwan.tawseel.modules.cart

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.visible
import com.example.sarwan.tawseel.repository.customer.CartRepository
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : BaseFragment<CartRepository>(R.layout.fragment_cart) {

    override val repository: CartRepository = getRepository(CartRepository::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        setObservers()
    }

    override fun setObservers() {
        repository.cartItemListener.foreverObserver(Observer {
            handleCartItemAddition()
        })
    }

    private fun handleCartItemAddition() {
        (cart_recycler_view?.adapter as? CartItemAdapter)?.apply {
            addItems(repository.getList())
            checkForEmptyRecyclerView(itemCount == 0)
        }
    }

    private fun checkForEmptyRecyclerView(empty: Boolean) {
        empty_layout?.visible(empty)
        non_empty_layout?.visible(!empty)
    }

    override fun initViews(view: View?) {
        cart_recycler_view?.apply {
            layoutManager = LinearLayoutManager(getBaseActivity(),RecyclerView.VERTICAL, false)
            adapter = CartItemAdapter(getBaseActivity(), ArrayList())
        }
    }
}