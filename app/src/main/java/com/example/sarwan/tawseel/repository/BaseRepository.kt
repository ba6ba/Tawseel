package com.example.sarwan.tawseel.repository

import android.content.Context
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.entities.HistoryMode
import com.example.sarwan.tawseel.entities.Profile
import com.example.sarwan.tawseel.repository.history.HistoryRepository
import com.example.sarwan.tawseel.utils.DummyData
import com.example.sarwan.tawseel.utils.GlobalData
import com.example.sarwan.tawseel.utils.GlobalData.PREFS_NAME
import com.example.sarwan.tawseel.utils.mapProfileToHistory
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

open class BaseRepository() {

    constructor(context: Context) : this() {
        this.context = context
    }

    private var context: Context ? = null

    protected var itemsInCart : Int = 0

    var profile : Profile ? = null

    private val preferences = context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val gson : Gson = Gson()

    fun saveLocationInPrefs(latLng : LatLng)  = preferences?.edit()?.putString(GlobalData.LATLNG, gson.toJson(latLng))?.apply()

    fun getLocationFromPrefs(success : (LatLng) -> Unit, failure : (String) -> Unit) {
        if (preferences?.contains(GlobalData.LATLNG) == true){
            gson.fromJson(preferences.getString(GlobalData.LATLNG, null), LatLng::class.java)?.apply {
                success(this)
            }?:failure("No LatLng found in shared preferences")
        }
    }

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
