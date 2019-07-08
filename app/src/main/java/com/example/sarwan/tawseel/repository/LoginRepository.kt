package com.example.sarwan.tawseel.repository

import android.content.Context
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.utils.Global

class LoginRepository () : BaseRepository() {

    var userNameType : Int = R.string.email

    fun userName(type : Int?)  {
        userNameType = if (type == Global.LOGIN_WITH_EMAIL) R.string.email else R.string.phone
    }
}