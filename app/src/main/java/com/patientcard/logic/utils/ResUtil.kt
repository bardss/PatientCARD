package com.patientcard.logic.utils

import android.graphics.drawable.Drawable
import com.patientcard.views.base.ApplicationContext

object ResUtil {

    fun getString(resourceId: Int): String? {
        return ApplicationContext.appContext?.getString(resourceId)
    }

    fun getDrawable(resourceId: Int): Drawable? {
        return ApplicationContext.appContext?.getDrawable(resourceId)
    }

    fun getColor(resourceId: Int): Int? {
        return ApplicationContext.appContext?.resources?.getColor(resourceId)
    }

    fun getBoolean(resourceId: Int): Boolean? {
        return ApplicationContext.appContext?.resources?.getBoolean(resourceId)
    }

    fun getDimen(resourceId: Int): Float? {
        return ApplicationContext.appContext?.resources?.getDimension(resourceId)
    }

}