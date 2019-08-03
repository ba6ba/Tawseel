package com.example.sarwan.tawseel.repository.business

import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.repository.BaseRepository

class HomeRepository : BaseRepository() {

    var onEditLiveData : MutableLiveData<Boolean> = MutableLiveData()
}