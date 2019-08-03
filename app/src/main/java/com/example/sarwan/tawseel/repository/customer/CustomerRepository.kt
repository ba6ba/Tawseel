package com.example.sarwan.tawseel.repository.customer

import com.example.sarwan.tawseel.entities.DummyData
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.repository.history.HistoryRepository

open class CustomerRepository : HistoryRepository() {

    fun addInCart() { itemsInCart+=1 }

    fun removeFromCart() {
        if (itemsInCart > 0)
            itemsInCart-=1
    }

    fun getCartValue() = itemsInCart

    private var dummyData : DummyData? = null

    fun fromBundle(obj : Any?) {  dummyData = obj as DummyData
    }

    fun getData() = dummyData

    fun getDriverName() = "Kifayatullah"

    fun getVehicleNumber() = "ICT-139"

    enum class StoresCategories { All , Food , Restaurants , Bakery }

    fun getStoreCategoriesTitle() = StoresCategories.values().toList().map { it.name }

    fun getStoreList() = com.example.sarwan.tawseel.utils.DummyData.makeStoreDummyData()

    enum class VendorCategories { All , New , Starters , Italian }

    fun getVendorCategoriesTitle() = VendorCategories.values().toList().map { it.name }

    fun getVendorsList() = com.example.sarwan.tawseel.utils.DummyData.makeVendorDummyData()
}