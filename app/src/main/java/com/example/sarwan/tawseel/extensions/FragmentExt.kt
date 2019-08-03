package com.example.sarwan.tawseel.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.sarwan.tawseel.base.BaseActivity
import com.example.sarwan.tawseel.base.BaseDialog
import com.example.sarwan.tawseel.repository.BaseRepository

fun Fragment.navigate(resId: Int, bundle: Bundle? = null) {
    NavHostFragment.findNavController(this).navigate(resId, bundle)
}

fun Fragment.navigateToBack(){
    NavHostFragment.findNavController(this).popBackStack()
}

fun <T : BaseRepository> Fragment.getActualActivity() = activity as BaseActivity<T>

fun <T : BaseRepository> Fragment.show(dialog: BaseDialog<T>, tag : String = dialog::class.java.simpleName) {
    dialog.show(getActualActivity<T>().supportFragmentManager, tag)
}
