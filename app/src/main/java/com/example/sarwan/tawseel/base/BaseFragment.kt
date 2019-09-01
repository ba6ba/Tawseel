package com.example.sarwan.tawseel.base

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.interfaces.TawseelLayout
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.GlobalData
import com.example.sarwan.tawseel.extensions.navigate
import com.example.sarwan.tawseel.extensions.navigateToBack
import com.example.sarwan.tawseel.interfaces.Resources
import java.io.Serializable


abstract class BaseFragment<T : BaseRepository>(private val layoutId: Int) :
    Fragment(), TawseelLayout, com.example.sarwan.tawseel.interfaces.Fragment<T>, Resources {

    private lateinit var baseActivity: BaseActivity<T>
    protected lateinit var repository: T

    abstract fun createRepoInstance()

    open fun bundleOnCreated(bundle: Bundle?) {}
    open fun activityCreated(savedInstanceState: Bundle?) {}
    open fun singleParamSerializable(serializable: Serializable?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = (activity as BaseActivity<T>)
        createRepoInstance()
        activityCreated(savedInstanceState)
        bundleOnCreated(arguments)
        singleParamSerializable(arguments?.getSerializable(GlobalData.PARAM))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityCreated(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(inflater, container = container, layoutId = layoutId)
    }

    protected fun getBaseActivity() = baseActivity

    override fun getRepository(t: Class<T>): T = getBaseActivity().getRepository(t)

    protected fun navigateTo(resId: Int, bundle: Bundle? = null, withDelay: Boolean = false) {
        if (!withDelay) navigate(resId, bundle) else Handler().postDelayed({
            navigateTo(
                resId,
                bundle,
                withDelay = false
            )
        }, GlobalData.SPLASH_DELAY)
    }

    fun navigateToMainApp() {
        navigateTo(getBaseActivity().repo.getActivityId(getBaseActivity().getAppRepository().userProfile))
        getBaseActivity().finish()
    }

    protected fun navigateBack() = navigateToBack()

    override fun getStringFromValues(resId: Int) = getBaseActivity().getStringFromValues(resId)

    override fun getColorFromValues(resId: Int) = getBaseActivity().getColorFromValues(resId)

    override fun getDimensionFromResources(resId: Int) = getBaseActivity().getDimensionFromResources(resId)

    override fun getIntegerFromResources(resId: Int) = getBaseActivity().getIntegerFromResources(resId)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getFontFromResources(resId: Int) = getBaseActivity().getFontFromResources(resId)

    override fun getDrawableFromResources(@DrawableRes resId: Int) = getBaseActivity().getDrawableFromResources(resId)


    fun <A> MutableLiveData<A>.foreverObserver(observer: Observer<A>) = observe(viewLifecycleOwner, observer)

    protected fun errorApiCall(message: String?) {
        getBaseActivity().showMessage(message)
    }

}
