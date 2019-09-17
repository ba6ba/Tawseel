package com.example.sarwan.tawseel.modules.business

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.responses.ItemListResponse
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.repository.business.BusinessRepository
import com.example.sarwan.tawseel.utils.GlobalData
import kotlinx.android.synthetic.main.fragment_business_item_details.*

class ItemDetailsFragment :
    BaseFragment<BusinessRepository>(R.layout.fragment_business_item_details) {

    private lateinit var itemDetails: ItemListResponse.Data

    override fun createRepoInstance() {
        repository = getRepository(BusinessRepository::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataToViews()
    }

    override fun dataToViews() {
        description?.applyText(itemDetails.itemDescription)
        name?.applyText(itemDetails.itemPrice?.toString())
        cover?.setImageURI(itemDetails.itemImage)
    }

    override fun bundleOnCreated(bundle: Bundle?) {
        itemDetails = bundle?.getSerializable(GlobalData.PARAM) as ItemListResponse.Data
    }
}