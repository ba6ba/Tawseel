package com.example.sarwan.tawseel.repository.driver

import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.CountDownTimer

class DriverNewOrderRepository : BaseRepository() {

    private val countDownTimer : MutableLiveData<String> = MutableLiveData()

    fun initTimer() = CountDownTimer(15000,1000, countDownTimer).start()

    fun getTimer() = countDownTimer
}