package com.example.jakubaniola.patientcard.access

import android.content.Intent
import com.example.jakubaniola.patientcard.base.BaseAbstractPresenter

class AccessPresenterImpl : BaseAbstractPresenter<AccessView>(), AccessPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}
