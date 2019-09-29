package com.example.sarwan.tawseel.modules.business

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.requests.ItemRequest
import com.example.sarwan.tawseel.extensions.actionOnClick
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.network.NetworkRepository
import com.example.sarwan.tawseel.repository.business.BusinessRepository
import kotlinx.android.synthetic.main.fragment_business_add_item.*

class AddItemFragment : BaseFragment<BusinessRepository>(R.layout.fragment_business_add_item) {

    private var addItemParams: ItemRequest = ItemRequest()
    private var enableButtonLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addItemParams.storeId = getProfileFromSharedPreference()?.business?._id
        setObservers()
        viewListeners()
    }

    override fun createRepoInstance() {
        repository = getRepository(BusinessRepository::class.java)
    }

    override fun setObservers() {
        repository.createUpdateItemApiInstance.foreverObserver(Observer {
            when (it) {
                is ApiResponse.Success -> {
                    showMessage("Item created successfully")
                    navigateTo(R.id.action_add_item_to_items_listing)
                }

                is ApiResponse.Error -> {
                    showMessage(it.message)
                }
            }
        })

        enableButtonLiveData.foreverObserver(Observer {
            add?.isEnabled = it
        })
    }

    override fun viewListeners() {
        add?.actionOnClick {
            repository.callCreateItemApi(addItemParams)
        }

        item_name_layout?.validationResult?.foreverObserver(Observer {
            enableButtonLiveData.value = it.result
            addItemParams.itemName = it.text
        })

        item_price_layout?.validationResult?.foreverObserver(Observer {
            addItemParams.itemPrice = it.text?.toInt()
            enableButtonLiveData.value = it.result
        })

        item_description?.validationResult?.foreverObserver(Observer {
            addItemParams.itemDescription = it.text
            enableButtonLiveData.value = it.result
        })
    }

}