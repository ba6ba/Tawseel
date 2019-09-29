package com.example.sarwan.tawseel.base

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.entities.UserProfile
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.GlobalData
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<T : BaseRepository> : AppCompatActivity() {

    lateinit var repo: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppRepository().userProfile = getProfileFromSharedPreference()
        if (getAppRepository().userProfile == null) {
            getAppRepository().userProfile = UserProfile()
        }
    }

    public fun getProfileFromSharedPreference() =
        getAppRepository().userProfile

    fun saveUserProfile() {
        getAppRepository().saveDataInSharedPreference(
            GlobalData.PROFILE, getAppRepository().userProfile as UserProfile
        )
    }

    protected fun logOut() {
        clearUserFromSharedPreference()
    }

    private fun clearUserFromSharedPreference() {
        clearUser()
    }

    private fun clearUser() {
        getAppRepository().saveDataInSharedPreference(GlobalData.PROFILE, UserProfile())
    }

    fun getAppRepository() = (application as Tawseel).getRepository()

    fun getRepository(t: Class<T>): T {
        return try {
            if (!::repo.isInitialized) {
                repo = t.newInstance()
            }
            t.cast(repo) as T
        } catch (e: Exception) {
            t.newInstance()
        }
    }

    fun showMessage(
        message: String? = resources.getString(R.string.something_went_wrong),
        length: Int = Toast.LENGTH_LONG
    ) =
        Toast.makeText(this, message, length).show()

    fun saveLocationInSharedPreferences(latLng: LatLng) =
        getAppRepository().saveLocationInPrefs(latLng)

    fun getLocationFromSharedPreferences(success: (LatLng) -> Unit, failure: (String) -> Unit) =
        getAppRepository().getLocationFromPrefs(success, failure)

    fun showSnackBar(
        mainTextStringId: Int, actionStringId: Int, length: Int = Snackbar.LENGTH_SHORT,
        listener: View.OnClickListener
    ) {
        Snackbar.make(findViewById(android.R.id.content), getString(mainTextStringId), length)
            .setAction(getString(actionStringId), listener).show()
    }

    fun getStringFromValues(resId: Int) = getString(resId)

    fun getColorFromValues(resId: Int) = getColor(resId)

    fun getDimensionFromResources(resId: Int) = resources.getDimension(resId)

    fun getIntegerFromResources(resId: Int) = resources.getInteger(resId)

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFontFromResources(resId: Int) = resources.getFont(resId)

    fun getDrawableFromResources(@DrawableRes resId: Int) = resources.getDrawable(resId, theme)

}
