package com.example.sarwan.tawseel.interfaces

import com.example.sarwan.tawseel.entities.UserProfile

interface ProfileProvider {
    fun provides(profile: UserProfile?)
}