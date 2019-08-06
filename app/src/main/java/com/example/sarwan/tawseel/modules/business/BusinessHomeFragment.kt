package com.example.sarwan.tawseel.modules.business

import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.actionOnClick
import com.example.sarwan.tawseel.extensions.booleanText
import com.example.sarwan.tawseel.extensions.textChangeListener
import com.example.sarwan.tawseel.extensions.visible
import com.example.sarwan.tawseel.repository.business.BusinessRepository
import kotlinx.android.synthetic.main.fragment_business_home.*

class BusinessHomeFragment : BaseFragment<BusinessRepository>(R.layout.fragment_business_home) {

    override val repository: BusinessRepository = getRepository(BusinessRepository::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewListeners()
        setObservers()
    }

    override fun viewListeners() {
        edit_business?.actionOnClick {
            getRepository(BusinessRepository::class.java).onEditLiveData.apply {
                postValue(value?.let { !it }?:true)
            }
        }

        business_name_layout.textChangeListener {
            getRepository(BusinessRepository::class.java).businessName.postValue(it)
        }

        business_description_layout.textChangeListener {
            getRepository(BusinessRepository::class.java).businessDescription.postValue(it)
        }
    }

    override fun setObservers() {
        getRepository(BusinessRepository::class.java).apply {
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
        edit_business.booleanText(edit, getStringFromValues(R.string.done), getStringFromValues(R.string.edit))
        edit_profile_image.visible(edit)
    }
}