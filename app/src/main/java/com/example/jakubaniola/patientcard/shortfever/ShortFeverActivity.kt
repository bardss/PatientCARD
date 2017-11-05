package com.example.jakubaniola.patientcard.shortfever

import com.example.jakubaniola.patientcard.R
import com.example.jakubaniola.patientcard.base.BasePresenter
import com.example.jakubaniola.patientcard.demographic.ShortFeverPresenterImpl
import com.rsqtechnologies.rsqphysio.base.BaseActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter

@ActivityView(layout = R.layout.activity_short_fever, presenter = ShortFeverPresenterImpl::class)
class ShortFeverActivity : BaseActivity(), ShortFeverView {

    @Presenter
    lateinit var presenter: ShortFeverPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

}
