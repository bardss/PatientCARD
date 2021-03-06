package com.patientcard.logic.utils

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

object ToastUtil {

    fun show(context: Context?, message: String?, duration: Int) {
        val toast = Toast.makeText(context, message, duration)
        val toastLayout = toast.view as LinearLayout
        val toastTextView = toastLayout.getChildAt(0) as TextView
        toastTextView.gravity = Gravity.CENTER
        toast.show()
    }

}