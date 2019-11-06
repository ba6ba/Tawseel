package com.example.sarwan.tawseel.helper

import com.example.sarwan.tawseel.entities.UserProfile
import com.example.sarwan.tawseel.entities.requests.LocationRequest
import com.example.sarwan.tawseel.interfaces.ProfileProvider

object LocationHelper : ProfileProvider {

    private var profile : UserProfile? = null

    override fun provides(profile: UserProfile?) {
        this.profile = profile
    }
    
    fun makeLocationRequest(
        latitude: Double = profile?.userLocation?.lat ?: 0.0,
        longitude: Double = profile?.userLocation?.lat ?: 0.0
    ) = LocationRequest(latitude, longitude, profile?.user?._id ?: "")
}