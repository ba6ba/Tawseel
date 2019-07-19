package com.example.sarwan.tawseel.repository.customer

import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.DummyData

class StoresRepository : BaseRepository() {

    enum class StoresCategories { All , Food , Restaurants , Bakery }

    fun getCategoriesTitle() = StoresCategories.values().toList().map { it.name }

    fun getStoreList() = DummyData.makeStoreDummyData()

}