package com.patientcard.logic.utils

import com.patientcard.views.base.ApplicationContext

object DpPxUtil {

    fun px2dp(px: Float): Float {
        return px / ApplicationContext.appContext?.resources?.displayMetrics?.density!!
    }

    fun dp2px(dp: Float): Float {
        return dp * ApplicationContext.appContext?.resources?.displayMetrics?.density!!
    }
}