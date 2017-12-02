package com.patientcard.addrecommendation

import android.content.Intent
import com.patientcard.R
import com.patientcard.base.BaseActivity
import com.patientcard.base.BasePresenter
import com.patientcard.observations.ObservationsActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_add_observation.*

@ActivityView(layout = R.layout.activity_add_observation, presenter = AddRecommendationPresenterImpl::class)
class AddRecommendationActivity : BaseActivity(), AddRecommendationView {

    @Presenter
    lateinit var presenter: AddRecommendationPresenter

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
