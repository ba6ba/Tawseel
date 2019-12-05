package com.example.sarwan.tawseel.modules.customer

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseDialog
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.repository.customer.CustomerRepository
import kotlinx.android.synthetic.main.dialog_customer_confirmation.*

class CustomerConfirmDialog : BaseDialog<CustomerRepository>(R.layout.dialog_customer_confirmation) {
    override fun createRepoInstance() {
        repository = getRepository(CustomerRepository::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewListeners()
    }

    override fun viewListeners() {
        reject?.navigateOnClick {
            action = false
            dismiss()
        }

        accept?.navigateOnClick {
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
        private var instance: CustomerConfirmDialog? = null

        @JvmStatic
        fun newInstance() = CustomerConfirmDialog().apply {
            instance = this
            return instance!! // It can't be null at this time
        }

        @JvmStatic
        fun getInstance(): CustomerConfirmDialog {
            return instance?.let { it } ?: kotlin.run { newInstance() }
        }
    }
}