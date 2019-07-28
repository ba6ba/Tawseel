package com.example.sarwan.tawseel.repository.onBoarding

import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.GlobalData

class LoginRepository () : BaseRepository() {

    var userNameType : Int = R.string.email

    fun userName(type : Int?)  {
        userNameType = if (type == GlobalData.LOGIN_WITH_EMAIL) R.string.email else R.string.phone
    }
}