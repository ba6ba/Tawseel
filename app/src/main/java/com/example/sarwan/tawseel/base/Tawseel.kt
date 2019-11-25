package com.example.sarwan.tawseel.base

import android.app.Application
import com.example.sarwan.tawseel.interfaces.ContextProvider
import com.example.sarwan.tawseel.network.RetrofitCustomResponse
import com.example.sarwan.tawseel.repository.AppRepository
import com.example.sarwan.tawseel.repository.BaseRepository
import com.facebook.drawee.backends.pipeline.Fresco

class Tawseel : Application() {

    private lateinit var appRepository: AppRepository
    override fun onCreate() {
        super.onCreate()
        appRepository = AppRepository(this)

        Fresco.initialize(this)
    }

    fun getRepository() = appRepository

    fun setRepository(repository: AppRepository) = this::appRepository.set(repository)

}
