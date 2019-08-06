package com.example.sarwan.tawseel.repository.common

import com.example.sarwan.tawseel.entities.enums.HistoryMode
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.DummyData
import com.example.sarwan.tawseel.utils.mapProfileToHistory

class HistoryRepository() : BaseRepository(){

    val mode: HistoryMode? by lazy {
        profile?.mapProfileToHistory()
    }

    fun getHistoryList(mode: HistoryMode) = if (mode == HistoryMode.BUSINESS) DummyData.makeBusinessHistory() else DummyData.makeNonBusinessHistory()
}