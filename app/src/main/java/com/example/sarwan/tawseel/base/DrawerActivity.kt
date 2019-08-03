package com.example.sarwan.tawseel.base

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.extensions.actionOnClick
import com.example.sarwan.tawseel.extensions.getConiditonDrawable
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.repository.driver.DriverRepository
import com.google.android.material.navigation.NavigationView

abstract class DrawerActivity<T : BaseRepository>(private val layout : Int) : BaseActivity<T>(){

    private var navHostFragment : NavHostFragment ? = null
    private var drawer_layout : DrawerLayout ? = null
    private var navigation_view : NavigationView ? = null
    private var toolBarIcon : ImageView ? = null
    private var backIconVisible : MutableLiveData<Boolean> = MutableLiveData()

    abstract fun activityCreated(savedInstanceState: Bundle?)
    abstract fun toolbarTitleChange(text : String?)
    abstract fun toolbarIconChange(drawable: Drawable)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        activityCreated(savedInstanceState)
        setupViews()
        clickListener()
        setObserver()
        setupNavigation()
        navigationListener()
    }

    private fun setObserver() {
        backIconVisible.observe(this, Observer {
            lockDrawer(it)
        })
    }

    private fun lockDrawer(lock: Boolean?){
        drawer_layout?.setDrawerLockMode(if(lock == true) DrawerLayout.LOCK_MODE_LOCKED_CLOSED else DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    private fun clickListener() {
        toolBarIcon?.actionOnClick {
            if (backIconVisible.value == true){
                performBackAction()
            }
            else {
                toggleDrawer()
            }
        }
    }

    private fun performBackAction() {
        onBackPressed()
    }

    private fun navigationListener() {
        navHostFragment?.navController?.addOnDestinationChangedListener { controller, destination, arguments ->
            backIconVisible.postValue(controller.graph.startDestination != destination.id)
            toolbarTitleChange(destination.label?.toString())
            toolbarIconChange(getConiditonDrawable(controller.graph.startDestination == destination.id, R.drawable.ic_navigation_white_24dp, R.drawable.back))

        }
    }

    private fun setupViews() {
        backIconVisible.value = false
        drawer_layout = findViewById(R.id.drawer_layout)
        navigation_view = findViewById(R.id.navigation_view)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as? NavHostFragment
        toolBarIcon = findViewById(R.id.toolbar_icon)
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

    private fun openDrawer() {
        drawer_layout?.openDrawer(GravityCompat.START, true)
    }

    private fun closeDrawer() {
        drawer_layout?.closeDrawer(GravityCompat.START, true)
    }

    fun toggleDrawer() {
        if (drawer_layout?.isDrawerOpen(GravityCompat.START) == true) {
            closeDrawer()
        }else {
            openDrawer()
        }
    }
}