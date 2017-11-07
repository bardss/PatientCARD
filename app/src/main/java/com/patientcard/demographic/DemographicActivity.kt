package com.patientcard.demographic

import android.content.Intent
import com.patientcard.R
import com.patientcard.base.BaseActivity
import com.patientcard.base.BasePresenter
import com.patientcard.shortfever.ShortFeverActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_demographic.*

@ActivityView(layout = R.layout.activity_demographic, presenter = DemographicPresenterImpl::class)
class DemographicActivity : BaseActivity(), DemographicView {

    @Presenter
    lateinit var presenter: DemographicPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        setupMenuClicks()
    }

    private fun setupMenuClicks() {
        feverMenuRelativeLayout.setOnClickListener {
            startActivity(Intent(this, ShortFeverActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        ordersMenuRelativeLayout.setOnClickListener {

        }
        observationMenuRelativeLayout.setOnClickListener {

        }
    }

}
