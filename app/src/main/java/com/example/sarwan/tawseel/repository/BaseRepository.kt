package com.example.sarwan.tawseel.repository

import android.content.Context
import com.example.sarwan.tawseel.utils.Global
import com.example.sarwan.tawseel.utils.Global.PREFS_NAME
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

class BaseRepository(private val context : Context) {

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val gson : Gson = Gson()

    fun saveLocationInPrefs(latLng : LatLng)  = preferences.edit().putString(Global.LATLNG, gson.toJson(latLng)).apply()


    fun getLocationFromPrefs(success : (LatLng) -> Unit, failure : (String) -> Unit) {
        if (preferences.contains(Global.LATLNG)){
            gson.fromJson(preferences.getString(Global.LATLNG, null), LatLng::class.java)?.apply {
                success(this)
            }?:failure("No LatLng found in shared preferences")
        }
    }
}