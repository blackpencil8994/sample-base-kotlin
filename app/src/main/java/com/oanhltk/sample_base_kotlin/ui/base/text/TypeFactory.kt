package com.oanhltk.sample_base_kotlin.ui.base.text

import android.content.Context
import android.graphics.Typeface

class TypeFactory(context: Context) {
    private val E_BOLD = "Excelorate_400.otf"
    private val M_THIN = "Metropolis-Thin.otf"
    private val M_LIGHT = "Metropolis-Light.otf"
    private val M_REGULAR = "Metropolis-Regular.otf"
    private val M_MEDIUM = "Metropolis-Medium.otf"
    private val M_BOLD = "Metropolis-Bold.otf"
    private val M_EXTRA_BOLD = "Metropolis-ExtraBold.otf"

    var excelorateBold: Typeface
    var metropolisThin: Typeface
    var metropolisLight: Typeface
    var metropolisRegular: Typeface
    var metropolisMedium: Typeface
    var metropolisBold: Typeface
    var metropolisExtraBold: Typeface

    init {
        excelorateBold = Typeface.createFromAsset(context.assets, E_BOLD)
        metropolisThin = Typeface.createFromAsset(context.assets, M_THIN)
        metropolisLight = Typeface.createFromAsset(context.assets, M_LIGHT)
        metropolisRegular = Typeface.createFromAsset(context.assets, M_REGULAR)
        metropolisMedium = Typeface.createFromAsset(context.assets, M_MEDIUM)
        metropolisBold = Typeface.createFromAsset(context.assets, M_BOLD)
        metropolisExtraBold = Typeface.createFromAsset(context.assets, M_EXTRA_BOLD)
    }
}