package com.example.sarwan.tawseel.utils

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.TimeUnit

class CountDownTimer(millisInFuture: Long,
                     countDownInterval: Long,
                     private val timerText : MutableLiveData<String>
                     ) : CountDownTimer(millisInFuture, countDownInterval) {

    override fun onTick(millisUntilFinished: Long) {
        timerText.postValue(
                String.format("%02d : %02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))
        )
    }

    override fun onFinish() {
        timerText.postValue(GlobalData.FINISH_TIME)
    }
}