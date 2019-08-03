package com.example.sarwan.tawseel.base

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.extensions.getConiditonDrawable
import com.google.android.material.navigation.NavigationView

abstract class DrawerActivity(private val layout : Int) : BaseActivity(){

    private var navHostFragment : NavHostFragment ? = null
    private var drawer_layout : DrawerLayout ? = null
    private var navigation_view : NavigationView ? = null

    abstract fun activityCreated(savedInstanceState: Bundle?)
    abstract fun toolbarTitleChange(text : String?)
    abstract fun toolbarIconChange(drawable: Drawable)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        activityCreated(savedInstanceState)
        setupViews()
        setupNavigation()
        navigationListener()
    }

    private fun navigationListener() {
        navHostFragment?.navController?.addOnDestinationChangedListener { controller, destination, arguments ->
            toolbarTitleChange(destination.label?.toString())
            toolbarIconChange(getConiditonDrawable(controller.graph.startDestination == destination.id ,
                R.drawable.ic_navigation_white_24dp, R.drawable.back))

        }
    }

    private fun setupViews() {
        drawer_layout = findViewById(R.id.drawer_layout)
        navigation_view = findViewById(R.id.navigation_view)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as? NavHostFragment
    }

    override fun onBackPressed() {
        if (drawer_layout?.isDrawerOpen(GravityCompat.START) == true){
            drawer_layout?.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }

    private fun setupNavigation() {
        navigation_view?.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawer_layout?.closeDrawers()
            true
        }

        navigation_view?.setupWithNavController(findNavController(R.id.main_container))
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(findNavController(R.id.main_container), drawer_layout)
    }
}