package com.patientcard.customviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.KeyEvent
import android.widget.EditText

class TitilliumLightEditText : EditText {

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
        val tf = Typeface.createFromAsset(context.assets, "fonts/TitilliumWeb-Light.ttf")
        typeface = tf
        setMaxLinesInput(1)
    }

    fun customSetText(text: CharSequence) {
        setText("")
        append(text)
    }

    private fun setMaxLinesInput(maxLines: Int) {
        setOnKeyListener { v, keyCode, event ->
            if (keyCode === KeyEvent.KEYCODE_ENTER && event.getAction() === KeyEvent.ACTION_UP) {
                val text = getText()!!.toString()
                val editTextRowCount = text.split("\\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size
                if (editTextRowCount >= maxLines) {
                    val lastBreakIndex = text.lastIndexOf("\n")
                    if (lastBreakIndex >= 0) {
                        val newText = text.substring(0, lastBreakIndex)
                        setText("")
                        append(newText)
                    }
                }
            }
            false
        }
    }

    companion object {
        fun getText(editText: EditText): String? {
            return if (editText.getText() != null) {
                editText.getText().toString()
            } else {
                null
            }
        }
    }
}