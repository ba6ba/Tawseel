package com.example.sarwan.tawseel.modules.business

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.*
import com.example.sarwan.tawseel.repository.business.BusinessRepository
import kotlinx.android.synthetic.main.fragment_business_home.*

class BusinessHomeFragment : BaseFragment<BusinessRepository>(R.layout.fragment_business_home) {

    override fun createRepoInstance() {
        repository = getRepository(BusinessRepository::class.java)
    }

    private fun checkIfStoreIsCreatedAlready() {
        getBaseActivity().getAppRepository().userProfile?.business?.let {
            navigateTo(R.id.action_business_home_to_business_details)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        checkIfStoreIsCreatedAlready()
        dataToViews()
        viewListeners()
        setObservers()
    }

    override fun dataToViews() {
        business_description?.text = getProfileFromSharedPreference()?.business?.storeDescription
            ?: repository.DEFAULT_BUSINESS_NAME
        business_name?.text = getProfileFromSharedPreference()?.business?.storeName
            ?: repository.DEFAULT_BUSINESS_DESCRIPTION
    }

    override fun viewListeners() {
        edit_business?.actionOnClick {
            repository.onEditLiveData.apply {
                postValue(value?.let { !it } ?: true)
            }
        }

        business_name_layout.textChangeListener {
            repository.businessName.postValue(it)
        }

        business_description_layout.textChangeListener {
            repository.businessDescription.postValue(it)
        }
    }

    override fun setObservers() {
        repository.apply {
            onEditLiveData.observe(this@BusinessHomeFragment, Observer {
                showScreenInEditMode(it)
            })

            businessName.observe(this@BusinessHomeFragment, Observer {
                business_name.text = it
            })

            businessDescription.observe(this@BusinessHomeFragment, Observer {
                business_description.text = it
            })
        }
    }

    private fun showScreenInEditMode(edit: Boolean) {
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
}