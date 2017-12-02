package com.patientcard.observations

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.patientcard.R
import com.patientcard.addobservation.AddObservationActivity
import com.patientcard.base.BaseActivity
import com.patientcard.base.BasePresenter
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_observations.*

@ActivityView(layout = R.layout.activity_observations, presenter = ObservationsPresenterImpl::class)
class ObservationsActivity : BaseActivity(), ObservationsView {

    @Presenter
    lateinit var presenter: ObservationsPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        setupShortFeverList()
        setupButtons()
    }

    private fun setupButtons() {
        addFab.setOnClickListener {
            startActivity(Intent(this, AddObservationActivity::class.java))
        }
    }

    private fun setupShortFeverList() {
        observationsRecyclerView.layoutManager = LinearLayoutManager(this)
        observationsRecyclerView.adapter = ObservationsAdapter()
    }

}
