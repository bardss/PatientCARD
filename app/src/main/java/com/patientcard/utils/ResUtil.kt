package com.patientcard.utils

import android.graphics.drawable.Drawable
import com.patientcard.base.ApplicationContext

object ResUtil {

    fun getString(resourceId: Int): String? {
        return ApplicationContext.appContext?.getString(resourceId)
    }

    fun getDrawable(resourceId: Int): Drawable? {
        return ApplicationContext.appContext?.getDrawable(resourceId)
    }

    fun getColor(resourceId: Int): Int? {
        return ApplicationContext.appContext?.getResources()?.getColor(resourceId)
    }

    fun getBoolean(resourceId: Int): Boolean? {
        return ApplicationContext.appContext?.getResources()?.getBoolean(resourceId)
    }

    fun getDimen(resourceId: Int): Float? {
        return ApplicationContext.appContext?.getResources()?.getDimension(resourceId)
    }

}