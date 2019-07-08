package com.example.sarwan.tawseel.base

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sarwan.tawseel.interfaces.TawseelLayout
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.Global
import com.example.sarwan.tawseel.utils.navigate
import com.example.sarwan.tawseel.utils.navigateToBack
import com.google.gson.reflect.TypeToken
import kotlin.reflect.KClass


abstract class BaseFragment <T : BaseRepository>( private val layoutId: Int) : Fragment(), TawseelLayout {

    private lateinit var baseActivity : BaseActivity
    private var repo : T ? = null

    open fun getBundleOnCreated(bundle: Bundle?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = (activity as BaseActivity)
        getBundleOnCreated(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(inflater, container = container, layoutId = layoutId)
    }

    private fun createView(inflater : LayoutInflater, layoutId : Int, container: ViewGroup?) : View? = inflater.inflate(layoutId, container, false)

    protected fun getBaseActivity() = baseActivity

    protected fun getRepository(t : Class<T>) : T{
        return if (repo == null) { repo = t.newInstance() ; repo?:t.newInstance() } else repo?:t.newInstance()
    }

    protected fun navigateTo(resId : Int , bundle : Bundle ? = null ,withDelay : Boolean = false) {
        if (!withDelay) navigate(resId, bundle) else Handler().postDelayed({ navigateTo(resId, bundle, withDelay = false)}, Global.SPLASH_DELAY)
    }

    protected fun navigateBack() = navigateToBack()

}