package com.example.sarwan.tawseel.repository.history

import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.entities.History
import com.example.sarwan.tawseel.entities.HistoryMode
import com.example.sarwan.tawseel.entities.Profile
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.DummyData
import com.example.sarwan.tawseel.utils.GlobalData
import com.example.sarwan.tawseel.utils.mapProfileToHistory

abstract class HistoryRepository() : BaseRepository(){

    val mode: HistoryMode? by lazy {
        profile?.mapProfileToHistory()
    }

    fun getHistoryList(mode: HistoryMode) = if (mode == HistoryMode.BUSINESS) DummyData.makeBusinessHistory() else DummyData.makeNonBusinessHistory()
}