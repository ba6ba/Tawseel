package com.example.sarwan.tawseel.entities.responses

import com.example.sarwan.tawseel.network.Error
import java.io.Serializable

open class GeneralResponse : Serializable {
    val success: Boolean = false
    val error: Error? = null
}
