package com.example.sarwan.tawseel.repository.main

import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.DummyData

class VendorRepository() : BaseRepository() {

    enum class VendorCategories { All , New , Starters , Italian }

    fun getCategoriesTitle() = VendorCategories.values().toList().map { it.name }

    fun getVendorsList() = DummyData.makeVendorDummyData()
}