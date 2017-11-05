package com.example.jakubaniola.patientcard.demographic

import android.content.Intent
import com.example.jakubaniola.patientcard.base.BaseAbstractPresenter
import com.example.jakubaniola.patientcard.shortfever.ShortFeverPresenter
import com.example.jakubaniola.patientcard.shortfever.ShortFeverView

class ShortFeverPresenterImpl : BaseAbstractPresenter<ShortFeverView>(), ShortFeverPresenter {

    override fun initExtras(intent: Intent) {
        // no extras
    }

}
