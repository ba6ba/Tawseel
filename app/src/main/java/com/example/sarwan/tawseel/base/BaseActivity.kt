package com.example.sarwan.tawseel.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sarwan.tawseel.R
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity(){

    override fun onBackPressed() {
        finish()
    }

    fun getRepository() = (application as Tawseel).getRepository()

    fun showMessage(message : String = resources.getString(R.string.something_went_wrong), length: Int = Toast.LENGTH_LONG)  =
        Toast.makeText(this,message, length).show()

    fun saveLocationInSharedPreferences(latLng : LatLng) = getRepository().saveLocationInPrefs(latLng)

    fun getLocationFromSharedPreferences(success : (LatLng) -> Unit, failure : (String) -> Unit) = getRepository().getLocationFromPrefs(success, failure)

    fun showSnackBar(mainTextStringId: Int, actionStringId: Int, length : Int = Snackbar.LENGTH_SHORT,
                     listener: View.OnClickListener) {
        Snackbar.make(findViewById(android.R.id.content), getString(mainTextStringId), length)
            .setAction(getString(actionStringId), listener).show()
    }

}
