package com.example.sarwan.tawseel.base

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sarwan.tawseel.repository.BaseRepository
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

class Tawseel : Application() {

    private lateinit var baseRepository: BaseRepository
    override fun onCreate() {
        super.onCreate()
        baseRepository = BaseRepository(this)
    }

    fun getRepository() = baseRepository

}
