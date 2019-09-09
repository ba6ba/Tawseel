package com.example.sarwan.tawseel.repository.business

import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.modules.business.BusinessItemCategories
import com.example.sarwan.tawseel.repository.BaseRepository

class BusinessRepository : BaseRepository() {

    val DEFAULT_BUSINESS_NAME = "Business Name"
    val DEFAULT_BUSINESS_DESCRIPTION = "Business Description"

    var onEditLiveData: MutableLiveData<Boolean> = MutableLiveData()

    var businessName: MutableLiveData<String> = MutableLiveData()
    var businessDescription: MutableLiveData<String> = MutableLiveData()

    fun getBusinessItemTitles() = BusinessItemCategories.values().toList().map { it.name }
    fun getBusinessItemsList() = com.example.sarwan.tawseel.utils.DummyData.makeVendorDummyData()
}