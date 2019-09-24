package com.example.sarwan.tawseel.modules.business

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.DrawerActivity
import com.example.sarwan.tawseel.entities.enums.GetStoreType
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.extensions.navigate
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.repository.business.BusinessRepository
import kotlinx.android.synthetic.main.activity_business.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class BusinessActivity : DrawerActivity<BusinessRepository>(R.layout.activity_business) {

    override fun activityCreated(savedInstanceState: Bundle?) {
        //
        getProfileFromSharedPreference()?.business?.let {
            main_container?.findNavController()?.setGraph(R.navigation.business_nav_graph_details)
        }?:kotlin.run {
            main_container?.findNavController()?.setGraph(R.navigation.business_nav_graph_home)
        }
    }

    override fun toolbarTitleChange(text: String?) {
        toolbar_title?.applyText(text)
    }

    override fun toolbarIconChange(drawable: Drawable) {
        toolbar_icon?.setImageDrawable(drawable)
    }

    override fun getNavigationMenuId(): Int = R.menu.buisness_side_navigation_menu

}