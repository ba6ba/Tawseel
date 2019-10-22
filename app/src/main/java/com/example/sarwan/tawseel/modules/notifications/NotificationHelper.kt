package com.example.sarwan.tawseel.modules.notifications

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.base.Tawseel
import com.example.sarwan.tawseel.entities.UserProfile
import com.example.sarwan.tawseel.entities.requests.FcmRequest
import com.example.sarwan.tawseel.entities.responses.GeneralResponse
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.network.NetworkRepository
import com.example.sarwan.tawseel.network.RetrofitCustomResponse
import com.example.sarwan.tawseel.utils.GlobalData

class NotificationHelper(private val context: Context) {

    fun processToken(token: String) {
        (context as Tawseel).getRepository().apply {
            saveDataInSharedPreference(GlobalData.FCM_TOKEN, token)
            fcmTokenApi(
                FcmRequest(
                    getFromSharedPreference<UserProfile>(GlobalData.PROFILE)?.user?._id ?: "",
                    fcmToken = token
                )
            )
        }
    }

    private fun fcmTokenApi(params: FcmRequest): LiveData<ApiResponse<GeneralResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<GeneralResponse>> = MutableLiveData()
        NetworkRepository.getInstance().fcmToken(params)
            .enqueue(object : RetrofitCustomResponse<GeneralResponse>(responseLiveData) {})
        return responseLiveData
    }
}