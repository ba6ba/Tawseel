package com.example.sarwan.tawseel.network

import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.entities.responses.GeneralResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

abstract class RetrofitCustomResponse<T>(private val responseLiveData: MutableLiveData<ApiResponse<T>>) :
    retrofit2.Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        responseLiveData.postValue(ApiResponse.error(t.localizedMessage))
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        val generalResponse = response.body() as? GeneralResponse
        if (response.isSuccessful && generalResponse?.success == true) {
            responseLiveData.postValue(ApiResponse.success(response.body()))
        } else {
            responseLiveData.postValue(
                ApiResponse.error(
                    generalResponse?.error?.msg ?: apiErrorBody(
                        response.errorBody()
                    ).error.msg
                )
            )

        }
    }

    private fun apiErrorBody(response: ResponseBody?) =
        Gson().fromJson(
            response?.charStream(),
            object : TypeToken<ErrorResponse>() {}.type
        ) as ErrorResponse
}