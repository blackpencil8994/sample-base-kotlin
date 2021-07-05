package com.oanhltk.sample_base_kotlin.ui.base.text

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.oanhltk.sample_base_kotlin.R

class CustomTextView : AppCompatTextView {
    private var mFontFactory: TypeFactory? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        applyCustomFont(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        applyCustomFont(context, attrs)
    }

    constructor(context: Context?) : super(context!!) {}

    private fun applyCustomFont(context: Context, attrs: AttributeSet?) {
        val typefaceType: Int
        val array = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.CustomTextView,
                0, 0)
        typefaceType = try {
            array.getInteger(R.styleable.CustomTextView_font_name, 0)
        } finally {
            array.recycle()
        }
        if (!isInEditMode) {
            typeface = getTypeFace(typefaceType)
        }
    }

    fun getTypeFace(type: Int): Typeface {
        if (mFontFactory == null) mFontFactory = TypeFactory(context)
        return when (type) {
            Constants.E_BOLD -> mFontFactory!!.excelorateBold
            Constants.M_THIN -> mFontFactory!!.metropolisThin
            Constants.M_LIGHT -> mFontFactory!!.metropolisLight
            Constants.M_REGULAR -> mFontFactory!!.metropolisRegular
            Constants.M_MEDIUM -> mFontFactory!!.metropolisMedium
            Constants.M_BOLD -> mFontFactory!!.metropolisBold
            Constants.M_EXTRA_BOLD -> mFontFactory!!.metropolisExtraBold
            else -> mFontFactory!!.metropolisRegular
        }
    }

    interface Constants {
        companion object {
            const val E_BOLD = 1
            const val M_THIN = 2
            const val M_LIGHT = 3
            const val M_REGULAR = 4
            const val M_MEDIUM = 5
            const val M_BOLD = 6
            const val M_EXTRA_BOLD = 7
        }
    }
}