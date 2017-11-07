package com.patientcard.shortfever

import com.patientcard.R
import com.patientcard.base.BaseActivity
import com.patientcard.base.BasePresenter
import com.patientcard.demographic.ShortFeverPresenterImpl
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_short_fever.*

@ActivityView(layout = R.layout.activity_short_fever, presenter = ShortFeverPresenterImpl::class)
class ShortFeverActivity : BaseActivity(), ShortFeverView {

    @Presenter
    lateinit var presenter: ShortFeverPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        setupShortFeverAdapter()
    }

    private fun setupShortFeverAdapter() {
        shortFeverRecyclerView.adapter = ShortFeverAdapter()
    }

}
