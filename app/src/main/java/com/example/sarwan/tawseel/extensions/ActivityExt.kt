package com.example.sarwan.tawseel.extensions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.sarwan.tawseel.R


fun AppCompatActivity.getConiditonDrawable(condition : Boolean, onTrue : Int, onFalse : Int) = resources.getDrawable(if (condition) onTrue else onFalse)

fun AppCompatActivity.navigate(resId: Int, bundle: Bundle? = null) {
    Navigation.findNavController(this, R.id.toolbar).navigate(resId, bundle)
}