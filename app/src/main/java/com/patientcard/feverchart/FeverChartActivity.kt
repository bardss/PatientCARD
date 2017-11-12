package com.patientcard.feverchart

import com.patientcard.R
import com.patientcard.base.BaseActivity
import com.patientcard.base.BasePresenter
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter



@ActivityView(layout = R.layout.activity_short_fever, presenter = FeverChartPresenterImpl::class)
class FeverChartActivity : BaseActivity(), FeverChartView {

    @Presenter
    lateinit var presenter: FeverChartPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

}
