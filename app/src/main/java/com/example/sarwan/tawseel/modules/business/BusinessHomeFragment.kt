package com.example.sarwan.tawseel.modules.business

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.requests.StoreRequest
import com.example.sarwan.tawseel.entities.responses.StoreResponse
import com.example.sarwan.tawseel.extensions.*
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.repository.business.BusinessRepository
import kotlinx.android.synthetic.main.fragment_business_home.*

class BusinessHomeFragment : BaseFragment<BusinessRepository>(R.layout.fragment_business_home) {

    private var businessName: String? = ""
    private var businessDescription: String? = ""

    override fun createRepoInstance() {
        repository = getRepository(BusinessRepository::class.java)
    }

    private fun setFields() {
        businessName =
            getProfileFromSharedPreference()?.business?.storeName?.isNullOrEmpty(repository.DEFAULT_BUSINESS_NAME)
        businessDescription =
            getProfileFromSharedPreference()?.business?.storeDescription?.isNullOrEmpty(repository.DEFAULT_BUSINESS_DESCRIPTION)
        dataToViews()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setFields()
        viewListeners()
        setObservers()
    }

    override fun dataToViews() {
        business_description?.text = businessDescription
        business_name?.text = businessName
        business_description_layout?.editText?.setText(businessDescription)
        business_name_layout?.editText?.setText(businessName)
    }

    override fun viewListeners() {
        edit_business?.actionOnClick {
            repository.onEditLiveData.apply {
                postValue(value?.let { !it } ?: true)
            }
        }

        business_name_layout.textChangeListener {
            businessName = it
        }

        business_description_layout.textChangeListener {
            businessDescription = it
        }
    }

    override fun setObservers() {
        repository.apply {
            onEditLiveData.observe(this@BusinessHomeFragment, Observer {
                showScreenInEditMode(it)
            })
        }

        repository.createUpdateStoreApiInstance.observe(viewLifecycleOwner, Observer {
            when(it) {
                is ApiResponse.Success -> {
                    //TODO - remove this enabled
                    edit_business?.isEnabled = true
                    getProfileFromSharedPreference()?.business = it.data?.data
                    getBaseActivity().saveUserProfile()
                    setFields()
                }

                is ApiResponse.Error -> {
                    //TODO - remove this enabled
                    edit_business?.isEnabled = true
                    showMessage(it.message)
                }
            }
        })
    }

    private fun showScreenInEditMode(edit: Boolean) {
        hitUpdateApi(edit)
        business_name.visible(!edit)
        business_name_layout.visible(edit)
        business_description.visible(!edit)
        business_description_layout.visible(edit)
        edit_business.booleanText(
            edit,
            getStringFromValues(R.string.done),
            getStringFromValues(R.string.edit)
        )
        edit_profile_image.visible(edit)
    }

    private fun hitUpdateApi(done: Boolean) {
        if (!done) {
            edit_business?.isEnabled = false
            repository.callUpdateStoreApi(
                getProfileFromSharedPreference()?.business?._id ?: "",
                makeStoreRequest(getProfileFromSharedPreference()?.business)
            )
        }
    }

    private fun makeStoreRequest(business: StoreResponse.Data?): StoreRequest {
        val request = StoreRequest()
        request.ownerId = business?.ownerId
        request.storeName = businessName
        request.categoryId = business?.categoryId
        request.storeAddress = business?.storeAddress
        request.storeDescription = businessDescription
        return request
    }
}