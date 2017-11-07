package com.patientcard.customviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

class TitilliumRegularTextView : android.support.v7.widget.AppCompatTextView {
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
            val tf = Typeface.createFromAsset(context.assets, "fonts/TitilliumWeb-Regular.ttf")
            typeface = tf
        }
    }
}