package com.patientcard.addobservation

import android.content.Intent
import com.patientcard.R
import com.patientcard.base.BaseActivity
import com.patientcard.base.BasePresenter
import com.patientcard.observations.ObservationsActivity
import com.patientcard.recommendations.AddObservationView
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_add_observation.*

@ActivityView(layout = R.layout.activity_add_observation, presenter = AddObservationPresenterImpl::class)
class AddObservationActivity : BaseActivity(), AddObservationView {

    @Presenter
    lateinit var presenter: AddObservationPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        setupSaveRecommendationClick()
    }

    private fun setupSaveRecommendationClick() {
        checkFab.setOnClickListener {
            startActivity(Intent(this, ObservationsActivity::class.java))
            finish()
        }
    }

}
