package com.example.sarwan.tawseel.entities

import com.example.sarwan.tawseel.entities.enums.ProfileType
import com.example.sarwan.tawseel.entities.responses.StoreResponse
import com.google.android.gms.maps.model.LatLng
import java.io.Serializable

class UserProfile {
    var user: User? = null
    var profileType: ProfileType? = null
    var token: String? = null
    var isLoggedIn = false
    var business: StoreResponse.Data? = null
    var userLocation : UserLocation = UserLocation()
}

class UserLocation : Serializable, Difference {

    override fun hasDifference(oldValue: Any, newValue: Any, action : (Boolean) -> Unit) : Any {
        action(newValue != oldValue)
        return if (newValue != oldValue) newValue else oldValue
    }

    fun setLocation(latLng : LatLng, action : (Boolean) -> Unit) {
        lat = hasDifference(lat, latLng.latitude, action) as Double
        lng = hasDifference(lng, latLng.longitude, action) as Double
    }
    var lat : Double = 0.0
    var lng : Double = 0.0
}

interface Difference {
    fun hasDifference(oldValue : Any, newValue : Any, action : (Boolean) -> Unit) : Any
}