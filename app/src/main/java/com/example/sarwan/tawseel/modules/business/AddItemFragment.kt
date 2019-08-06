package com.example.sarwan.tawseel.modules.business

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.repository.business.BusinessRepository

class AddItemFragment : BaseFragment<BusinessRepository>(R.layout.fragment_business_add_item) {

    override val repository: BusinessRepository = getRepository(BusinessRepository::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}