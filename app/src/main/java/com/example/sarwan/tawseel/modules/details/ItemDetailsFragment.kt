package com.example.sarwan.tawseel.modules.details

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.actionOnClick
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.extensions.show
import com.example.sarwan.tawseel.interfaces.DialogInteraction
import com.example.sarwan.tawseel.modules.dialogs.ConfirmationDialog
import com.example.sarwan.tawseel.repository.main.ConfirmationRepository
import com.example.sarwan.tawseel.repository.main.ItemDetailsRepository
import com.example.sarwan.tawseel.repository.main.NotificationsRepostiory
import com.example.sarwan.tawseel.repository.main.VendorRepository
import com.example.sarwan.tawseel.utils.Global
import kotlinx.android.synthetic.main.fragment_item_details.*

class ItemDetailsFragment : BaseFragment<ItemDetailsRepository>(R.layout.fragment_item_details), DialogInteraction {

    override fun dismissCallBack(result: Boolean) {
        actionOnDialogButton(result)
    }

    private fun actionOnDialogButton(result: Boolean) {
        if (!result) navigateBack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataToViews()
        viewListeners()
    }

    override fun viewListeners() {
        order?.actionOnClick {
            show(ConfirmationDialog.newInstance())
        }
    }

    override fun dataToViews() {
        getRepository(ItemDetailsRepository::class.java).getData()?.let {
            description?.applyText(it.description)
        }
    }

    override fun getBundleOnCreated(bundle: Bundle?) {
        getRepository(ItemDetailsRepository::class.java).fromBundle(Global.PARAM)
    }
}