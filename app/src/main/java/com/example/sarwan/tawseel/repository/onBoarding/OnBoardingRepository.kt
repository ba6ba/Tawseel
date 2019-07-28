package com.example.sarwan.tawseel.repository.onBoarding

import android.os.Bundle
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.GlobalData

class OnBoardingRepository() : BaseRepository() {

    val phoneBundle = Bundle(1).apply { putInt(GlobalData.PARAM, GlobalData.LOGIN_WITH_PHONE) }

    val emailBundle = Bundle(1).apply { putInt(GlobalData.PARAM, GlobalData.LOGIN_WITH_EMAIL) }
}