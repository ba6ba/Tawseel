package com.example.sarwan.tawseel.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.entities.UserProfile
import com.example.sarwan.tawseel.entities.enums.ProfileType
import com.example.sarwan.tawseel.entities.requests.FcmRequest
import com.example.sarwan.tawseel.entities.requests.LocationRequest
import com.example.sarwan.tawseel.entities.requests.NotificationRequest
import com.example.sarwan.tawseel.entities.responses.GeneralResponse
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.network.NetworkRepository
import com.example.sarwan.tawseel.network.RetrofitCustomResponse

abstract class BaseRepository() {

    private var _notificationApiInstance: MediatorLiveData<ApiResponse<GeneralResponse>> =
        MediatorLiveData()
    var notificationApiInstance: MutableLiveData<ApiResponse<GeneralResponse>> =
        _notificationApiInstance


    private var _locationApiInstance: MediatorLiveData<ApiResponse<GeneralResponse>> =
        MediatorLiveData()
    var locationApiInstance: MutableLiveData<ApiResponse<GeneralResponse>> =
        _locationApiInstance


    fun callNotificationApi(params: NotificationRequest) {
        _notificationApiInstance.addSource(notificationApi(params)) {
            _notificationApiInstance.value = it
        }
    }

    private fun notificationApi(params: NotificationRequest): LiveData<ApiResponse<GeneralResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<GeneralResponse>> = MutableLiveData()
        NetworkRepository.getInstance().notification(params)
            .enqueue(object : RetrofitCustomResponse<GeneralResponse>(responseLiveData) {})
        return responseLiveData
    }


    fun callLocationApi(params: LocationRequest) {
        _locationApiInstance.addSource(locationApi(params)) {
            _locationApiInstance.value = it
        }
    }

    private fun locationApi(params: LocationRequest): LiveData<ApiResponse<GeneralResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<GeneralResponse>> = MutableLiveData()
        NetworkRepository.getInstance().location(params)
            .enqueue(object : RetrofitCustomResponse<GeneralResponse>(responseLiveData) {})
        return responseLiveData
    }


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
}