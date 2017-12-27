package com.patientcard.views.demographic

import android.content.Intent
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.PatientDTO
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import com.patientcard.views.observations.ObservationsActivity
import com.patientcard.views.recommendations.RecommendationsActivity
import com.patientcard.views.shortfever.ShortFeverActivity
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

    override fun fillFields(patient: PatientDTO) {
        nameTextView.text = patient.name + " " + patient.surname
        patientCodeTextView.text = patient.patientCode
    }

    override fun setupMenuButtons(qrCode: String?) {
        feverMenuRelativeLayout.setOnClickListener {
            startActivity(Intent(this, ShortFeverActivity::class.java)
                    .putExtra(IntentKeys.QR_CODE, qrCode))
        }
        recommendationsMenuRelativeLayout.setOnClickListener {
            startActivity(Intent(this, RecommendationsActivity::class.java)
                    .putExtra(IntentKeys.QR_CODE, qrCode))
        }
        observationsMenuRelativeLayout.setOnClickListener {
            startActivity(Intent(this, ObservationsActivity::class.java)
                    .putExtra(IntentKeys.QR_CODE, qrCode))
        }
    }

}
