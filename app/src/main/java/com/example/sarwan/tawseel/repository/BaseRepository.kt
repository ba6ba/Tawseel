package com.example.sarwan.tawseel.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseActivity
import com.example.sarwan.tawseel.entities.UserProfile
import com.example.sarwan.tawseel.entities.enums.ProfileType
import com.example.sarwan.tawseel.entities.requests.LocationRequest
import com.example.sarwan.tawseel.entities.requests.NotificationApiRequest
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


    fun callNotificationApi(locationParams : LocationRequest, params: NotificationApiRequest, activity: BaseActivity<*>) {
        _notificationApiInstance.addSource(locationApi(locationParams, activity)) {
            _notificationApiInstance.addSource(riderSearchApi(params, activity)) {
                _notificationApiInstance.value = it
            }
        }
    }

    private fun riderSearchApi(
        params: NotificationApiRequest,
        activity: BaseActivity<*>
    ): LiveData<ApiResponse<GeneralResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<GeneralResponse>> = MutableLiveData()
        NetworkRepository.getInstance().riderSearch(params)
            .enqueue(object : RetrofitCustomResponse<GeneralResponse>(responseLiveData,activity) {})
        return responseLiveData
    }


    fun callLocationApi(params: LocationRequest?, activity: BaseActivity<*>) {
        params?.let {
            _locationApiInstance.addSource(locationApi(params, activity)) {
                _locationApiInstance.value = it
            }
        }
    }

    private fun locationApi(
        params: LocationRequest,
        activity: BaseActivity<*>
    ): LiveData<ApiResponse<GeneralResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<GeneralResponse>> = MutableLiveData()
        NetworkRepository.getInstance().location(params)
            .enqueue(object : RetrofitCustomResponse<GeneralResponse>(responseLiveData, null) {})
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