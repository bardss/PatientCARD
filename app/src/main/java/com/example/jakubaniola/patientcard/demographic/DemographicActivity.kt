package com.example.jakubaniola.patientcard.demographic

import android.content.Intent
import com.example.jakubaniola.patientcard.R
import com.example.jakubaniola.patientcard.base.BasePresenter
import com.example.jakubaniola.patientcard.shortfever.ShortFeverActivity
import com.rsqtechnologies.rsqphysio.base.BaseActivity
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
        fever_menu_relative_layout.setOnClickListener {
            startActivity(Intent(this, ShortFeverActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        orders_menu_relative_layout.setOnClickListener {

        }
        observation_menu_relative_layout.setOnClickListener {

        }
    }

}
