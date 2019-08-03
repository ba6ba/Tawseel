package com.example.sarwan.tawseel.repository.driver

import com.example.sarwan.tawseel.helper.extras.MapCameraAttributes
import com.example.sarwan.tawseel.repository.BaseRepository

class DriverRepository : BaseRepository() {

    private val mapCameraAttributes = MapCameraAttributes()

    fun getDefaultCameraAttributes() = mapCameraAttributes.build()

}

