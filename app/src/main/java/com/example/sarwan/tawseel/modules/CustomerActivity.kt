package com.example.sarwan.tawseel.modules

import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseActivity
import kotlinx.android.synthetic.main.activity_customer.*

class CustomerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)
        setupMainNavigation()
    }

    private fun setupMainNavigation() {
        bottomNavigation.setupWithNavController(findNavController(R.id.main_container))
    }

}