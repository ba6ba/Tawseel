package com.example.sarwan.tawseel.modules.dialogs

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseDialog
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.repository.main.ConfirmationRepository
import kotlinx.android.synthetic.main.dialog_confirmation.*

class ConfirmationDialog : BaseDialog<ConfirmationRepository>(R.layout.dialog_confirmation) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewListeners()
    }

    override fun viewListeners() {
        no?.navigateOnClick {
            action = false
            dismiss()
        }

        yes?.navigateOnClick {
            action = true
            dismiss()
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
        private var instance : ConfirmationDialog ? = null
        @JvmStatic
        fun newInstance() = ConfirmationDialog().apply {
            instance = this
            return instance!! // It can't be null at this time
        }

        @JvmStatic
        fun getInstance() : ConfirmationDialog {
            return instance?.let { it }?:kotlin.run { ConfirmationDialog.newInstance() }
        }
    }
}