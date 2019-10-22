package com.example.sarwan.tawseel.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.sarwan.tawseel.entities.UserProfile
import com.example.sarwan.tawseel.extensions.get
import com.example.sarwan.tawseel.utils.GlobalData
import com.example.sarwan.tawseel.utils.GlobalData.PREFS_NAME
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

open class AppRepository(private val context : Context) {

    var preferences : SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val gson : Gson = Gson()
    var userProfile : UserProfile? = null

    fun saveLocationInPrefs(latLng : LatLng)  = preferences.edit()?.putString(GlobalData.LATLNG, gson.toJson(latLng))?.apply()

    fun getLocationFromPrefs(success : (LatLng) -> Unit, failure : (String) -> Unit) {
        if (preferences.contains(GlobalData.LATLNG)){
            gson.fromJson(preferences.getString(GlobalData.LATLNG, null), LatLng::class.java)?.apply {
                success(this)
            }?:failure("No LatLng found in shared preferences")
        }
    }

    fun saveDataInSharedPreference(key : String , data : Any) =
        preferences.edit(commit = false , action = {
            putString(key, gson.toJson(data))
    })

    inline fun <reified T> getFromSharedPreference(key : String) : T? =
        gson.fromJson(preferences.get(key), T::class.java)
}