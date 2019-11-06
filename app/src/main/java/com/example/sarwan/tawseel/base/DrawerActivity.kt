package com.example.sarwan.tawseel.base

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.entities.requests.FcmRequest
import com.example.sarwan.tawseel.entities.requests.LocationRequest
import com.example.sarwan.tawseel.extensions.actionOnClick
import com.example.sarwan.tawseel.extensions.getConiditonDrawable
import com.example.sarwan.tawseel.helper.LocationHelper
import com.example.sarwan.tawseel.interfaces.ProfileProvider
import com.example.sarwan.tawseel.modules.notifications.NotificationHelper
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.GlobalData
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.layout_header_nav_menu.view.*

abstract class DrawerActivity<T : BaseRepository>(private val layout: Int) : LocationActivity<T>() {

    private var navHostFragment: NavHostFragment? = null
    private var drawer_layout: DrawerLayout? = null
    private var navigation_view: NavigationView? = null
    private var toolBarIcon: ImageView? = null
    private var backIconVisible: MutableLiveData<Boolean> = MutableLiveData()
    private val BUSINESS_NAME_PLACEHOLDER = "[BUSINESS_NAME]"
    private val CUSTOMER_NAME_PLACEHOLDER = "[CUSTOMER_NAME]"
    private val DRIVER_NAME_PLACEHOLDER = "[DRIVER_NAME]"

    abstract fun activityCreated(savedInstanceState: Bundle?)
    abstract fun toolbarTitleChange(text: String?)
    abstract fun toolbarIconChange(drawable: Drawable)
    abstract fun getNavigationMenuId(): Int

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

    override fun onSuccess(latLng: LatLng) {
        getAppRepository().userProfile?.userLocation?.setLocation(latLng) {updated->
            if (updated) {
                repo.callLocationApi(getRequestParams(latLng))
            }
        }
    }

    private fun getRequestParams(latLng: LatLng): LocationRequest? = LocationHelper.makeLocationRequest(latLng.latitude, latLng.longitude)

    override fun onFailure(vararg error: String) {
        // show error to location fetch failed
    }

    private fun setObserver() {
        backIconVisible.observe(this, Observer {
            lockDrawer(it)
        })
    }

    private fun lockDrawer(lock: Boolean?) {
        drawer_layout?.setDrawerLockMode(if (lock == true) DrawerLayout.LOCK_MODE_LOCKED_CLOSED else DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    private fun clickListener() {
        toolBarIcon?.actionOnClick {
            if (backIconVisible.value == true) {
                performBackAction()
            } else {
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
            toolbarTitleChange(getHeaderTitle(destination.label?.toString()))
            toolbarIconChange(
                getConiditonDrawable(
                    controller.graph.startDestination == destination.id,
                    R.drawable.ic_navigation_white_24dp,
                    R.drawable.back
                )
            )
        }
    }

    private fun getHeaderTitle(header: String?): String? {
        if (header?.contains(BUSINESS_NAME_PLACEHOLDER) == true) {
            return getProfileFromSharedPreference()?.business?.storeName
        }

        if (header?.contains(DRIVER_NAME_PLACEHOLDER) == true) {
            return getProfileFromSharedPreference()?.user?.name
        }

        if (header?.contains(CUSTOMER_NAME_PLACEHOLDER) == true) {
            return getProfileFromSharedPreference()?.user?.name
        }

        return header
    }

    private fun setupViews() {
        backIconVisible.value = false
        drawer_layout = findViewById(R.id.drawer_layout)
        navigation_view = findViewById(R.id.navigation_view)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_container) as? NavHostFragment
        toolBarIcon = findViewById(R.id.toolbar_icon)
    }

    override fun onBackPressed() {
        if (drawer_layout?.isDrawerOpen(GravityCompat.START) == true) {
            drawer_layout?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setupNavigation() {
        navigation_view?.apply {
            inflateMenu(getNavigationMenuId())
            setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.actionHome -> {
                        closeDrawer()
                    }
                    R.id.actionLogout -> {
                        //logOut()
                        //navigateToLoginFragment()
                    }
                    R.id.actionHistory -> {

                    }
                    R.id.actionItemsList -> {

                    }
                    R.id.actionAddItem -> {

                    }
                    R.id.actionCart -> {

                    }
                    R.id.actionTracking -> {

                    }
                }
                it.isChecked = true
                closeDrawer()
                true
            }
            getHeaderView(0).profile_image?.actionOnClick {
                navigateToProfileFragment()
            }
            setupWithNavController(findNavController(R.id.main_container))
        }
    }

    private fun navigateToProfileFragment() {
        navHostFragment?.navController?.navigate(R.id.actionProfile)
    }

    private fun navigateToLoginFragment() {
        navHostFragment?.navController?.navigate(R.id.actionLogin)
    }

    private fun openDrawer() {
        drawer_layout?.openDrawer(GravityCompat.START, true)
    }

    private fun closeDrawer() {
        drawer_layout?.closeDrawer(GravityCompat.START, true)
    }

    private fun toggleDrawer() {
        if (drawer_layout?.isDrawerOpen(GravityCompat.START) == true) {
            closeDrawer()
        } else {
            openDrawer()
        }
    }
}