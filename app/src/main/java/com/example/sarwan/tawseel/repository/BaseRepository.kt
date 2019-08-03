package com.example.sarwan.tawseel.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.entities.HistoryMode
import com.example.sarwan.tawseel.entities.Profile
import com.example.sarwan.tawseel.extensions.get
import com.example.sarwan.tawseel.repository.history.HistoryRepository
import com.example.sarwan.tawseel.utils.DummyData
import com.example.sarwan.tawseel.utils.GlobalData
import com.example.sarwan.tawseel.utils.GlobalData.PREFS_NAME
import com.example.sarwan.tawseel.utils.mapProfileToHistory
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

open class BaseRepository() {

    protected var itemsInCart : Int = 0
    var profile : Profile ? = null

    fun getActivityId(): Int {
        return when(profile){
            Profile.CUSTOMER->{
                R.id.action_to_CustomerActivity
            }
            Profile.DRIVER->{
                R.id.action_to_DriverActivity
            }
            Profile.BUSINESS->{
                R.id.action_to_BusinessActivity
            }
            else ->{
                R.id.action_to_CustomerActivity
            }
        }
    }
}