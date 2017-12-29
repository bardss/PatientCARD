package com.patientcard.views.customviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import com.rey.material.widget.TextView

class TitilliumSemiBoldTextView : TextView {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        if (!isInEditMode) {
            val tf = Typeface.createFromAsset(context.assets, "fonts/TitilliumWeb-SemiBold.ttf")
            typeface = tf
        }
    }
}