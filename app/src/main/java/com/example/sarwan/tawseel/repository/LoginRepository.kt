package com.example.sarwan.tawseel.repository

import android.content.Context
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.utils.Global

class LoginRepository(val context: Context) : BaseRepository(context) {

    var userNameType : String = ""

    fun userName(type : Int?)  {
        userNameType = if (type == Global.LOGIN_WITH_EMAIL) context.getString(R.string.email) else context.getString(R.string.phone)
    }
}