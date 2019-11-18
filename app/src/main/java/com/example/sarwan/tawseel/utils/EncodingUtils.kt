package com.example.sarwan.tawseel.utils

import android.util.Base64
import com.google.gson.Gson

object EncodingUtils {

    @ExperimentalStdlibApi
    fun <T> encodeObjectToString(data: T): String {
        return try {
            Base64.encodeToString(Gson().toJson(data).encodeToByteArray(), Base64.DEFAULT)
        } catch (e: Exception) {
            data.toString()
        }
    }

    @ExperimentalStdlibApi
    inline fun <reified T> decodeObjectFromString(string: String): T {
        return try {
            val decodedString = Base64.decode(string, Base64.DEFAULT)
            Gson().fromJson(decodedString.decodeToString(), T::class.java)
        } catch (e: Exception) {
            T::class.java.newInstance()
        }
    }
}