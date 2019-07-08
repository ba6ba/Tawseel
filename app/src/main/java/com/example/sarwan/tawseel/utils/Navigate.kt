package com.example.sarwan.tawseel.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

fun Fragment.navigate(resId: Int, bundle: Bundle? = null) {
    NavHostFragment.findNavController(this).navigate(resId, bundle)
}

fun Fragment.navigateToBack(){
    NavHostFragment.findNavController(this).popBackStack()
}