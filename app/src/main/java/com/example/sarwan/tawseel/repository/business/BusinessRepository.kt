package com.example.sarwan.tawseel.repository.business

import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.repository.history.HistoryRepository

open class BusinessRepository : HistoryRepository() {
    var onEditLiveData : MutableLiveData<Boolean> = MutableLiveData()

    var businessName : MutableLiveData<String> = MutableLiveData()
    var businessDescription : MutableLiveData<String> = MutableLiveData()
}