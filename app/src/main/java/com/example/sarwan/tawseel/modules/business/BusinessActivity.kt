package com.example.sarwan.tawseel.modules.business

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseActivity
import com.example.sarwan.tawseel.base.DrawerActivity
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.repository.business.BusinessRepository
import kotlinx.android.synthetic.main.layout_toolbar.*

class BusinessActivity : DrawerActivity<BusinessRepository>(R.layout.activity_business) {

    override fun activityCreated(savedInstanceState: Bundle?) {}

    override fun toolbarTitleChange(text: String?) {
        toolbar_title?.applyText(text)
    }

    override fun toolbarIconChange(drawable: Drawable) {
        toolbar_icon?.setImageDrawable(drawable)
    }

    override fun getNavigationMenuId(): Int = R.menu.buisness_side_navigation_menu

}