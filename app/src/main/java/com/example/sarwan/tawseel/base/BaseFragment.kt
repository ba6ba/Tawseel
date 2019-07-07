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


abstract class BaseFragment<T : BaseRepository>( private val layoutId: Int) : Fragment(), TawseelLayout {

    private lateinit var baseActivity : BaseActivity
    private lateinit var repo : Class<T>

    open fun getBundleOnCreated(bundle: Bundle?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = (activity as BaseActivity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(inflater, container = container, layoutId = layoutId)
    }

    private fun createView(inflater : LayoutInflater, layoutId : Int, container: ViewGroup?) : View? = inflater.inflate(layoutId, container, false)

    protected fun getBaseActivity() = baseActivity

    protected fun getRepository() = repo.newInstance()

    protected fun navigateTo(resId : Int , bundle : Bundle ? = null ,withDelay : Boolean = false) {
        if (!withDelay) navigate(resId, bundle) else Handler().postDelayed({ navigateTo(resId, bundle, withDelay = false)}, Global.SPLASH_DELAY)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getBundleOnCreated(arguments)
    }
}