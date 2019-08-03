package com.example.sarwan.tawseel.base

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.extensions.navigate
import com.example.sarwan.tawseel.extensions.navigateToBack
import com.example.sarwan.tawseel.interfaces.DialogInteraction
import com.example.sarwan.tawseel.interfaces.TawseelLayout
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.GlobalData

abstract class BaseDialog <T : BaseRepository>( private val layoutId: Int) : DialogFragment() ,
    TawseelLayout, com.example.sarwan.tawseel.interfaces.Fragment<T>{

    private val TAG = "BaseDialog"
    private lateinit var baseActivity : BaseActivity<T>
    protected var dismissListener : DialogInteraction ? = null
    protected var action : Boolean = false

    private var repo : T ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as BaseActivity<T>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(inflater, container = container, layoutId = layoutId)
    }

    protected fun getBaseActivity() = baseActivity

    override fun getRepository(t : Class<T>) : T{
        return if (repo == null) { repo = t.newInstance() ; repo?:t.newInstance() } else repo?:t.newInstance()
    }

    fun dismissListener(listener : DialogInteraction) {
        dismissListener = listener
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (this.isAdded) {
            return  //or return false/true, based on where you are calling from
        }

        try {
            val ft = manager.beginTransaction()
            ft.add(this, TAG)
            ft.commitAllowingStateLoss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState).run {
            window?.attributes?.windowAnimations = R.style.Animation_Design_BottomSheetDialog
            window?.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(0))
            window?.attributes?.x = activity?.resources?.getDimensionPixelSize(R.dimen.x10)
            window?.attributes?.y = activity?.resources?.getDimensionPixelSize(R.dimen.x10)
            return this
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.resources?.let {
            dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog?.window?.setGravity(Gravity.CENTER)
        }
    }

    override fun dismiss() {
        try {
            if (isDismissListenerInstance()) dismissListener?.dismissCallBack(result = action)
            super.dismiss()
        }catch (e : Exception){
            dismissAllowingStateLoss()
        }
    }

    private fun isDismissListenerInstance(): Boolean {
        return try {
            fragmentManager?.fragments?.get(0)?.childFragmentManager?.fragments?.get(0) is DialogInteraction

        }catch (e : Exception){
            fragmentManager?.fragments?.get(0)?.childFragmentManager?.fragments?.get(0) is DialogInteraction
        }
    }

    protected fun navigateTo(resId : Int , bundle : Bundle ? = null ,withDelay : Boolean = false) {
        if (!withDelay) navigate(resId, bundle) else Handler().postDelayed({ navigateTo(resId, bundle, withDelay = false)}, GlobalData.SPLASH_DELAY)
    }

    protected fun navigateBack() = navigateToBack()

}