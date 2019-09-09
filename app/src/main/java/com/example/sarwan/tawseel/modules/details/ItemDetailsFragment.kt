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
import com.example.sarwan.tawseel.repository.customer.CustomerRepository
import com.example.sarwan.tawseel.utils.GlobalData
import kotlinx.android.synthetic.main.fragment_item_details.*

class ItemDetailsFragment : BaseFragment<CustomerRepository>(R.layout.fragment_item_details),
    DialogInteraction {

    override fun createRepoInstance() {
        repository = getRepository(CustomerRepository::class.java)
    }

    override fun dismissCallBack(result: Boolean) {
        actionOnDialogButton(result)
    }

    private fun actionOnDialogButton(result: Boolean) {
        if (!result) navigateTo(R.id.actionCart)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataToViews()
        viewListeners()
        setObservers()
    }

    override fun setObservers() {
        repository.apply {
            cartItemListener.foreverObserver(Observer {
                if (added) {
                    addToCart(getData())
                } else {
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