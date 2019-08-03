package com.example.sarwan.tawseel.modules.onBoarding

import android.os.Bundle
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseActivity
import com.example.sarwan.tawseel.base.Tawseel
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.repository.onBoarding.AuthenticationRepository

class AuthenticationActivity : BaseActivity<AuthenticationRepository>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
    }
}
