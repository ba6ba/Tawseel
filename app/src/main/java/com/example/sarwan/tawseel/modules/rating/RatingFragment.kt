package com.example.sarwan.tawseel.modules.rating

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.actionOnClick
import com.example.sarwan.tawseel.repository.rating.RatingRepository
import kotlinx.android.synthetic.main.fragment_rating.*

class RatingFragment : BaseFragment<RatingRepository>(R.layout.fragment_rating) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewListeners()
    }

    override fun viewListeners() {
        submit?.actionOnClick {
            navigateBack()
        }
    }
}