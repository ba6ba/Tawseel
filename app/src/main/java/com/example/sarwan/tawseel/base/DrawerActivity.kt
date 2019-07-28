package com.example.sarwan.tawseel.base

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sarwan.tawseel.R
import com.google.android.material.navigation.NavigationView

abstract class DrawerActivity(private val layout : Int) : BaseActivity(){

    private var navHostFragment : NavHostFragment ? = null
    private var drawer_layout : DrawerLayout ? = null
    private var navigation_view : NavigationView ? = null
    private var toolbar : Toolbar? = null

    abstract fun activityCreated(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        activityCreated(savedInstanceState)
        setupViews()
        setupNavigation()
    }

    private fun setupViews() {
        drawer_layout = findViewById(R.id.drawer_layout)
        navigation_view = findViewById(R.id.navigation_view)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as? NavHostFragment
        toolbar = findViewById(R.id.toolbar)
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

        setupActionBarWithNavController(findNavController(R.id.main_container), drawer_layout)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(findNavController(R.id.main_container), drawer_layout)
    }
}