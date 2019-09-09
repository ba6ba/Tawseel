package com.example.sarwan.tawseel.entities

import com.example.sarwan.tawseel.entities.enums.ProfileType

class UserProfile {
    var user : User ? = null
    var profileType: ProfileType? = null
    var token : String ? = null
    var isLoggedIn = false
}