package com.example.sarwan.tawseel.entities

import com.example.sarwan.tawseel.entities.enums.ProfileType
import com.example.sarwan.tawseel.entities.responses.StoreResponse

class UserProfile {
    var user: User? = null
    var profileType: ProfileType? = null
    var token: String? = null
    var isLoggedIn = false
    var business: StoreResponse.Data? = null
}