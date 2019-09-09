package com.example.sarwan.tawseel.repository

import android.app.Activity
import android.app.Application
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseActivity
import com.example.sarwan.tawseel.base.Tawseel
import com.example.sarwan.tawseel.entities.User
import com.example.sarwan.tawseel.entities.UserProfile
import com.example.sarwan.tawseel.entities.enums.ProfileType
import com.example.sarwan.tawseel.network.ErrorResponse
import com.example.sarwan.tawseel.utils.GlobalData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody

abstract class BaseRepository() {

    fun getActivityId(userProfile: UserProfile?): Int {
        return when (userProfile?.profileType) {
            ProfileType.CUSTOMER -> {
                R.id.action_to_CustomerActivity
            }
            ProfileType.DRIVER -> {
                R.id.action_to_DriverActivity
            }
            ProfileType.BUSINESS -> {
                R.id.action_to_BusinessActivity
            }
            else -> {
                R.id.action_to_CustomerActivity
            }
        }
    }

    fun apiErrorBody(response: ResponseBody?) =
        Gson().fromJson(
            response?.charStream(),
            object : TypeToken<ErrorResponse>() {}.type
        ) as ErrorResponse
}