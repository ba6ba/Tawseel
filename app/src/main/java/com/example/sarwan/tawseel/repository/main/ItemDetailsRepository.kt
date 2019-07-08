package com.example.sarwan.tawseel.repository.main

import com.example.sarwan.tawseel.entities.DummyData
import com.example.sarwan.tawseel.repository.BaseRepository

class ItemDetailsRepository : BaseRepository() {

    private var dummyData : DummyData ? = null

    fun fromBundle(obj : Any?) {  dummyData = obj as DummyData }

    fun getData() = dummyData
}