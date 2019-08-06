package com.example.sarwan.tawseel.repository

import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.entities.enums.Profile

abstract class BaseRepository() {

    var profile : Profile? = null

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