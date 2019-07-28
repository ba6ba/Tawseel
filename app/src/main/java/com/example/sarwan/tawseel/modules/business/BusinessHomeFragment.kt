package com.example.sarwan.tawseel.modules.business

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.actionOnClick
import com.example.sarwan.tawseel.extensions.booleanText
import com.example.sarwan.tawseel.extensions.visible
import com.example.sarwan.tawseel.repository.business.HomeRepository
import kotlinx.android.synthetic.main.fragment_business_home.*

class BusinessHomeFragment : BaseFragment<HomeRepository>(R.layout.fragment_business_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewListeners()
        dataToViews()
    }

    override fun viewListeners() {
        edit_business?.actionOnClick {
            getRepository(HomeRepository::class.java).onEditLiveData.apply {
                postValue(value?.let { !it }?:true)
            }
        }
    }

    override fun setObservers() {
        getRepository(HomeRepository::class.java).apply {
            showScreenInEditMode.observe(this@BusinessHomeFragment, Observer {
                showScreenInEditMode(it)
            })

            onEditLiveData.observe(this@BusinessHomeFragment, Observer {
                showScreenInEditMode.postValue(it)
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

    override fun dataToViews() {

    }

}