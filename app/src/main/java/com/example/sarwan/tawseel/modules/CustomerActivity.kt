package com.example.sarwan.tawseel.modules

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.DrawerActivity
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.repository.customer.CustomerRepository
import kotlinx.android.synthetic.main.activity_customer.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class CustomerActivity : DrawerActivity<CustomerRepository>(R.layout.activity_customer) {

    override fun activityCreated(savedInstanceState: Bundle?) {
        setupMainNavigation()
    }

    override fun toolbarTitleChange(text: String?) {
        toolbar_title?.applyText(text)
    }

    override fun toolbarIconChange(drawable: Drawable) {
        toolbar_icon?.setImageDrawable(drawable)
    }

    private fun setupMainNavigation() {
        bottomNavigation.setupWithNavController(findNavController(R.id.main_container))
    }

    override fun getNavigationMenuId(): Int = R.menu.customer_side_navigation_menu

}