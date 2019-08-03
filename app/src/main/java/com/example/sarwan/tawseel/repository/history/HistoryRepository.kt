package com.example.sarwan.tawseel.repository.history

import com.example.sarwan.tawseel.entities.HistoryMode
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.DummyData
import com.example.sarwan.tawseel.utils.mapProfileToHistory

class HistoryRepository : BaseRepository() {

    var mode : HistoryMode? = profile?.mapProfileToHistory()

    fun getHistoryList(mode : HistoryMode) = if (mode == HistoryMode.BUSINESS) DummyData.makeBusinessHistory() else DummyData.makeNonBusinessHistory()
}