package com.example.sarwan.tawseel.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.base.BaseActivity
import com.example.sarwan.tawseel.entities.responses.GeneralResponse
import com.example.sarwan.tawseel.interfaces.ContextProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

abstract class RetrofitCustomResponse<T>(private val responseLiveData: MutableLiveData<ApiResponse<T>>,
                                         private val activity: BaseActivity<*> ? = null) :
    retrofit2.Callback<T> {

    init {
        activity?.progressLiveData?.postValue(true)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        activity?.progressLiveData?.postValue(false)
        responseLiveData.postValue(ApiResponse.error(t.localizedMessage))
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        activity?.progressLiveData?.postValue(false)
        val generalResponse = response.body() as? GeneralResponse
        if (response.isSuccessful && generalResponse?.success == true) {
            responseLiveData.postValue(ApiResponse.success(response.body()))
        } else {
            responseLiveData.postValue(
                ApiResponse.error(
                    generalResponse?.error?.msg ?: try {
                        apiErrorBody(
                            response.errorBody()
                        ).error.msg
                    } catch (e: Exception) {
                        Log.e(
                            RetrofitCustomResponse::class.java.simpleName,
                            "Api unhandled error exception"
                        )
                        "Technical Error"
                    }
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