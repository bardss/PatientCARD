package com.patientcard.views.observations

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.ObservationDTO
import com.patientcard.views.addobservation.AddObservationActivity
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_observations.*

@ActivityView(layout = R.layout.activity_observations, presenter = ObservationsPresenterImpl::class)
class ObservationsActivity : BaseActivity(), ObservationsView {

    @Presenter
    lateinit var presenter: ObservationsPresenter

    private var observationsAdapter: ObservationsAdapter? = null

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        setupShortFeverList()
    }

    override fun onResume() {
        super.onResume()
        presenter.getObservations()
    }

    override fun setupButtons(patientId: String?) {
        addFab.setOnClickListener {
            startActivity(Intent(this, AddObservationActivity::class.java)
                    .putExtra(IntentKeys.PATIENT_ID, patientId))
        }
    }

    private fun setupShortFeverList() {
        observationsRecyclerView.layoutManager = LinearLayoutManager(this)
        observationsAdapter = ObservationsAdapter()
        observationsRecyclerView.adapter = observationsAdapter
    }

    override fun setObservationList(observations: List<ObservationDTO>) {
        observationsAdapter?.setObservations(observations)
    }

}
