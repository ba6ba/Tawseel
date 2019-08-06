package com.example.sarwan.tawseel.repository.business

import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.repository.BaseRepository

class BusinessRepository : BaseRepository() {
    var onEditLiveData : MutableLiveData<Boolean> = MutableLiveData()

    var businessName : MutableLiveData<String> = MutableLiveData()
    var businessDescription : MutableLiveData<String> = MutableLiveData()
}