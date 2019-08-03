package com.example.sarwan.tawseel.views

import android.content.Context
import android.util.AttributeSet
import com.example.sarwan.tawseel.R
import com.willy.ratingbar.ScaleRatingBar

class TawseelRatingBar @JvmOverloads constructor(context: Context ? = null, attributeSet: AttributeSet ? = null,
                                                 defStyleRes : Int = 0) : ScaleRatingBar(context,attributeSet,defStyleRes){

    private var NUM_STARS = 5

    init {
        initialize()
    }

    private fun initialize() {
        resources.getDrawable(R.drawable.ic_star_not_selected)?.let {emptyDrawable->
            setEmptyDrawable(emptyDrawable)
        }

        resources.getDrawable(R.drawable.ic_selected_star)?.let {filledDrawable->
            setFilledDrawable(filledDrawable)
        }

        numStars = NUM_STARS
    }
}