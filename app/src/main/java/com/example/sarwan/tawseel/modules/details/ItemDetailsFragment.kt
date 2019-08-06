package com.example.sarwan.tawseel.modules.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.actionOnClick
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.extensions.show
import com.example.sarwan.tawseel.interfaces.DialogInteraction
import com.example.sarwan.tawseel.modules.dialogs.ConfirmationDialog
import com.example.sarwan.tawseel.repository.customer.CartRepository
import com.example.sarwan.tawseel.utils.GlobalData
import kotlinx.android.synthetic.main.fragment_item_details.*

class ItemDetailsFragment : BaseFragment<CartRepository>(R.layout.fragment_item_details), DialogInteraction {

    override val repository: CartRepository = getRepository(CartRepository::class.java)

    override fun dismissCallBack(result: Boolean) {
        actionOnDialogButton(result)
    }

    private fun actionOnDialogButton(result: Boolean) {
        if (!result) navigateBack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataToViews()
        viewListeners()
        setObservers()
    }

    override fun setObservers() {
        repository.apply {
            cartItemListener.foreverObserver(Observer {
                if (added){
                    addToCart(getData())
                }else {
                    removeFromCart(getData())
                }
            })
        }
    }

    override fun viewListeners() {
        order?.actionOnClick {
            show(ConfirmationDialog.newInstance())
        }
    }

    override fun dataToViews() {
        repository.getData()?.let {
            description?.applyText(it.description)
        }
    }

    override fun bundleOnCreated(bundle: Bundle?) {
        repository.fromBundle(bundle?.getSerializable(GlobalData.PARAM))
    }
}