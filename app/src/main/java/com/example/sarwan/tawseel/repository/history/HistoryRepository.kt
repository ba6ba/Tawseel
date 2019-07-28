package com.example.sarwan.tawseel.repository.history

import com.example.sarwan.tawseel.entities.HistoryMode
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.DummyData

class HistoryRepository : BaseRepository() {

    lateinit var mode : HistoryMode

    fun getHistoryList(mode : HistoryMode) = if (mode == HistoryMode.BUSINESS) DummyData.makeBusinessHistory() else DummyData.makeNonBusinessHistory()
}