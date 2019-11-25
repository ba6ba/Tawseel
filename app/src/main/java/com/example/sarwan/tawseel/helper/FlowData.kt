package com.example.sarwan.tawseel.helper

import com.example.sarwan.tawseel.utils.EncodingUtils

object FlowData {

    var flowData : Any ? = null

    fun <T> setData(data : T) {
        flowData = data
    }

    @ExperimentalStdlibApi
    inline fun <reified T> getData() : T {
        return try {
            EncodingUtils.decodeObjectFromString(flowData as String)
        } catch (e : Exception) {
            T::class.java.newInstance()
        }
    }

}