package com.patientcard.views.addobservation

import android.content.Intent
import android.view.View
import android.widget.EditText
import com.patientcard.R
import com.patientcard.logic.model.transportobjects.ObservationDTO
import com.patientcard.logic.utils.AnimUtils
import com.patientcard.logic.utils.FormatTimeDateUtil
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import com.patientcard.views.observations.ObservationsActivity
import com.patientcard.views.recommendations.AddObservationView
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_add_observation.*
import kotlinx.android.synthetic.main.dialog_delete.*

@ActivityView(layout = R.layout.activity_add_observation, presenter = AddObservationPresenterImpl::class)
class AddObservationActivity : BaseActivity(), AddObservationView {

    @Presenter
    lateinit var presenter: AddObservationPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        setupSaveObservationClick()
    }

    override fun setPatientName(patientName: String?) {
        nameTextView.text = patientName
    }

    override fun setTitle(title: String?) {
        titleTextView.text = title
    }

    override fun fillFields(observation: ObservationDTO?) {
        observationDateTextView.text = ResUtil.getString(R.string.observation) + " " + FormatTimeDateUtil.getFormattedDateTime(observation?.dateTime)
        personValueTextView.setText(observation?.employee)
        noteEditText.setText(observation?.note)
    }

    override fun setupDeleteIcon(observation: ObservationDTO?) {
        deleteImageView.visibility = View.VISIBLE
        deleteImageView.setOnClickListener {
            AnimUtils.fadeIn(300, deleteDialogLayout)
            whatToDeleteTextView.text = ResUtil.getString(R.string.observation)
            whenToDeleteTextView.text = FormatTimeDateUtil.getFormattedDateTime(observation?.dateTime)
            setupDeleteDialogButtons()
        }
    }

    private fun setupDeleteDialogButtons() {
        cancelButton.setOnClickListener {
            AnimUtils.fadeOut(300, deleteDialogLayout)
        }
        deleteButton.setOnClickListener {
            presenter.deleteObservation()
        }
    }

    private fun setupSaveObservationClick() {
        checkFab.setOnClickListener {
            val validData = isEditTextEmpty(personValueTextView) && isEditTextEmpty(noteEditText)
            if (validData) presenter.saveObservation(getObservation())
        }
    }

    private fun getObservation(): ObservationDTO {
        val employee = personValueTextView.text.toString()
        val note = noteEditText.text.toString()
        return ObservationDTO(null, null, employee, null, note)
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
