package com.example.sarwan.tawseel.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.extensions.layoutParams
import com.example.sarwan.tawseel.extensions.takeWidth
import kotlinx.android.synthetic.main.layout_info_cell_with_divider.view.*


class TawseelInfoLayout @JvmOverloads constructor(context : Context,
                                                  private var attrs: AttributeSet ? = null,
                                                  private var defStyleAttr : Int = 0)
    : LinearLayout(context,attrs, defStyleAttr) {

    private var mTitle : String ? = null
    private var mSubTitle : String ? = null
    private var mDividerHeight : Float = 0f
    private var mDividerColor : Int = 0
    
    init {
        initialize()
    }

    private fun getAttributes() {
        context.theme.obtainStyledAttributes(attrs, R.styleable.TawseelInfoLayout, 0, 0).apply {
            try {
                mTitle = getString(R.styleable.TawseelInfoLayout_title)
                mSubTitle = getString(R.styleable.TawseelInfoLayout_subTitle)
                mDividerColor = getColor(R.styleable.TawseelInfoLayout_dividerColor, resources.getColor(R.color.colorAccent, context.theme))
                mDividerHeight = getDimension(R.styleable.TawseelInfoLayout_dividerHeight, 12f)
            } finally {
                recycle()
            }
        }
    }

    private fun initialize() {
        View.inflate(context, R.layout.layout_info_cell_with_divider, this)
        getAttributes()
        updateTitleText()
        updateSubTitleText()
        updateDividerHeight()
        updateDividerColor()
    }

    private fun updateDividerColor(color : Int = this::mDividerColor.get()) {
        divider?.setBackgroundColor(color)
    }

    private fun updateDividerHeight(height : Float = this::mDividerHeight.get()) {
        val dividerView = divider
        dividerView?.layoutParams(width = divider?.takeWidth(), height = height.toInt())
        parent_view?.removeView(divider)
        parent_view?.addView(dividerView)
    }

    fun updateSubTitleText(text : String ? = this::mSubTitle.name) {
        sub_title?.text = text
    }

    fun updateTitleText(text : String ? = this::mTitle.name) {
        title?.text = text
    }
}