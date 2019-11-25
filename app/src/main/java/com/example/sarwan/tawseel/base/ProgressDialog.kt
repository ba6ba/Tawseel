package com.example.sarwan.tawseel.base

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.modules.dialogs.ConfirmationDialog
import com.example.sarwan.tawseel.repository.BaseRepository
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.github.ybq.android.spinkit.style.MultiplePulseRing
import kotlinx.android.synthetic.main.fragment_progress_dialog.*
import java.lang.Exception

class ProgressDialog : BaseDialog<BaseRepository>(R.layout.fragment_progress_dialog) {

    override fun createRepoInstance() {
        //repository = getRepository(BaseRepository::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar?.setIndeterminateDrawable(DoubleBounce())
        isCancelable = false
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            if (this.isAdded) {
                return
            }
            val fragmentTransaction = manager.beginTransaction()
            try {
                fragmentTransaction.add(this, this::class.java.simpleName)
            }catch (e: Exception) {
                //
            }
            fragmentTransaction.commitAllowingStateLoss()
        } catch (e : Exception) {
            //
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.resources?.let {
            dialog?.window?.setLayout(resources.getDimension(R.dimen.x200).toInt(), resources.getDimension(R.dimen.x200).toInt())
            dialog?.window?.setGravity(Gravity.CENTER)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(0))
        }
    }

    override fun onDestroy() {
        instance = null
        super.onDestroy()
    }

    override fun onDetach() {
        instance = null
        super.onDetach()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ConfirmationDialog.
         */
        private var instance: ProgressDialog? = null

        @JvmStatic
        fun newInstance() = ProgressDialog().apply {
            instance = this
            return instance!! // It can't be null at this time
        }

        @JvmStatic
        fun getInstance(): ProgressDialog {
            return instance?.let { it } ?: kotlin.run { ProgressDialog.newInstance() }
        }
    }
}