package com.patientcard.logic.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

class AnimUtils {

    fun fadeIn(duration: Int, vararg views: View) {
        for (view in views) {
            view.alpha = 0f
            view.visibility = View.VISIBLE
            view.animate()
                    .alpha(1f)
                    .setDuration(duration.toLong())
                    .setListener(null)
        }
    }

    fun fadeOut(duration: Int, vararg views: View) {
        for (view in views) {
            view.animate()
                    .alpha(0f)
                    .setDuration(duration.toLong())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            view.visibility = View.GONE
                        }
                    })
        }
    }
}