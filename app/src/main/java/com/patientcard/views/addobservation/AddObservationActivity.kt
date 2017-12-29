package com.patientcard.views.addobservation

import android.content.Intent
import android.widget.EditText
import com.patientcard.R
import com.patientcard.logic.model.transportobjects.ObservationDTO
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import com.patientcard.views.observations.ObservationsActivity
import com.patientcard.views.recommendations.AddObservationView
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_add_observation.*
import org.threeten.bp.LocalDateTime

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
            val validData = isEditTextEmpty(personValueTextView) && isEditTextEmpty(noteEditText)
            if (validData) presenter.saveObservation(getObservation())
        }
    }

    private fun getObservation(): ObservationDTO {
        val employee = personValueTextView.text.toString()
        val note = noteEditText.text.toString()
        return ObservationDTO(null, null, employee, LocalDateTime.now(), note)
    }

    private fun isEditTextEmpty(editText: EditText): Boolean{
        if (editText.text.isEmpty()) {
            editText.error = ResUtil.getString(R.string.fill_data)
            return false
        }
        return true
    }

    override fun navigateBack() {
        startActivity(Intent(this, ObservationsActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
    }

}
