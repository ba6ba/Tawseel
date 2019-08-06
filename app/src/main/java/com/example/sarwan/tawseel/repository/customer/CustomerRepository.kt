package com.example.sarwan.tawseel.repository.customer

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.entities.DummyData
import com.example.sarwan.tawseel.entities.enums.StoresCategories
import com.example.sarwan.tawseel.entities.enums.VendorCategories
import com.example.sarwan.tawseel.extensions.decBy
import com.example.sarwan.tawseel.extensions.greaterTo
import com.example.sarwan.tawseel.extensions.incBy
import com.example.sarwan.tawseel.repository.BaseRepository

open class CustomerRepository : BaseRepository() {

    private lateinit var dummyData : DummyData

    fun fromBundle(obj : Any?) {
        dummyData = obj as DummyData
    }

    fun getData() = dummyData

    fun getDriverName() = "Kifayatullah"

    fun getVehicleNumber() = "ICT-139"

    fun getStoreCategoriesTitle() = StoresCategories.values().toList().map { it.name }

    fun getStoreList() = com.example.sarwan.tawseel.utils.DummyData.makeStoreDummyData()

    fun getVendorCategoriesTitle() = VendorCategories.values().toList().map { it.name }

    fun getVendorsList() = com.example.sarwan.tawseel.utils.DummyData.makeVendorDummyData()

    fun handleEmptyCartError() = ""
}