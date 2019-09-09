package com.example.sarwan.tawseel.entities

import com.example.sarwan.tawseel.entities.enums.ProfileType

class UserProfile {
    var user: User? = null
    var profileType: ProfileType? = null
    var token: String? = null
    var isLoggedIn = false
    var business: Business? = null
}

data class Business(
    var id: String? = null,
    var businessName: String? = null,
    var businessDescription: String? = null,
    var businessAddress: String? = null,
    var businessImage: String? = null
)