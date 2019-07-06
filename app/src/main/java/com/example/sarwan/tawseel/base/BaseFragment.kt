package com.example.sarwan.tawseel.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sarwan.tawseel.interfaces.TawseelLayout
import com.example.sarwan.tawseel.modules.onBoarding.SplashRepository
import com.example.sarwan.tawseel.repository.BaseRepository








abstract class BaseFragment<T : BaseRepository>( private val layoutId: Int) : Fragment(), TawseelLayout {

    private lateinit var baseActivity : BaseActivity
    private lateinit var repo : Class<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = (activity as BaseActivity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(inflater, container = container, layoutId = layoutId)
    }

    private fun createView(inflater : LayoutInflater, layoutId : Int, container: ViewGroup?) : View? = inflater.inflate(layoutId, container, false)

    fun getBaseActivity() = baseActivity

    fun getRepository() = repo.newInstance()

}