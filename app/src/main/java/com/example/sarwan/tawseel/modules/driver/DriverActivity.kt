package com.example.sarwan.tawseel.modules.driver

import android.os.Bundle
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.DrawerActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

class DriverActivity : DrawerActivity(R.layout.activity_driver) {

    override fun activityCreated(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
    }
}
