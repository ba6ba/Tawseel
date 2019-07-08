package com.example.sarwan.tawseel.repository

import android.content.Context
import android.os.Bundle
import com.example.sarwan.tawseel.utils.Global

class OnBoardingRepository() : BaseRepository() {

    val phoneBundle = Bundle(1).apply { putInt(Global.PARAM, Global.LOGIN_WITH_PHONE) }

    val emailBundle = Bundle(1).apply { putInt(Global.PARAM, Global.LOGIN_WITH_EMAIL) }
}