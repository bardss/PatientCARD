package com.patientcard.views.addobservation

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
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
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission
import com.yanzhenjie.permission.PermissionListener
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_add_observation.*
import kotlinx.android.synthetic.main.dialog_delete.*
import org.threeten.bp.LocalDateTime

@ActivityView(layout = R.layout.activity_add_observation, presenter = AddObservationPresenterImpl::class)
class AddObservationActivity : BaseActivity(), AddObservationView {

    @Presenter
    lateinit var presenter: AddObservationPresenter

    private var speechRecognizer: SpeechRecognizer? = null
    private var speechRecognizerIntent: Intent? = null
    private var isListening: Boolean = false

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        setupSaveObservationClick()
        setupMicrophoneButton()
    }

    override fun setPatientName(patientName: String?) {
        nameTextView.text = patientName
    }

    override fun setTitle(title: String?) {
        pageTitleTextView.text = title
    }

    override fun fillFields(observation: ObservationDTO?) {
        observationDateTextView.text = ResUtil.getString(R.string.observation) + " " + FormatTimeDateUtil.getFormattedDateTime(observation?.dateTime)
        personValueEditText.setText(observation?.employee)
        drugEditText.setText(observation?.note)
    }

    override fun setObservationLabel() {
        observationDateTextView.text = ResUtil.getString(R.string.observation) + " " + FormatTimeDateUtil.getFormattedDateTime(LocalDateTime.now())
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
            val validData = isEditTextEmpty(personValueEditText) && isEditTextEmpty(drugEditText)
            if (validData) presenter.saveObservation(getObservation())
        }
    }

    private fun getObservation(): ObservationDTO {
        val employee = personValueEditText.text.toString()
        val note = drugEditText.text.toString()
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

    private fun setupMicrophoneButton() {
        setSpeechRecognizer()
        microphoneImageView.setOnClickListener {
            if (!isListening) {
                checkPermission()
            } else {
                speechRecognizer?.stopListening()
            }
            isListening = !isListening
        }
    }

    private fun checkPermission() {
        AndPermission
                .with(this)
                .requestCode(0)
                .permission(*Permission.MICROPHONE)
                .callback(object : PermissionListener {
                    override fun onSucceed(requestCode: Int, grantPermissions: List<String>) {
                        speechRecognizer?.startListening(speechRecognizerIntent)
                    }

                    override fun onFailed(requestCode: Int, deniedPermissions: List<String>) {
                        //do nothing
                    }
                })
                .start()
    }

    private fun setSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent?.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechRecognizerIntent?.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                this.packageName)
        speechRecognizerIntent?.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)

        val listener = SpeechRecognitionListener()
        speechRecognizer?.setRecognitionListener(listener)
    }

    private inner class SpeechRecognitionListener : RecognitionListener {

        override fun onBeginningOfSpeech() {

        }

        override fun onBufferReceived(buffer: ByteArray) {

        }

        override fun onEndOfSpeech() {
            isListening = false
            microphoneImageView.setImageResource(R.drawable.microphone_not_working_icon)
        }

        override fun onError(error: Int) {
            if (error != SpeechRecognizer.ERROR_NO_MATCH) {
                isListening = false
                microphoneImageView.setImageResource(R.drawable.microphone_not_working_icon)
            }
        }

        override fun onEvent(eventType: Int, params: Bundle) {

        }

        override fun onPartialResults(partialResults: Bundle) {
            val result = "" + partialResults.get("results_recognition")!!
            if (result.length > 2) {
                drugEditText.setText(result.substring(1, result.length - 1))
            }
        }

        override fun onReadyForSpeech(params: Bundle) {
            microphoneImageView.setImageResource(R.drawable.microphone_working_icon)
        }

        override fun onResults(results: Bundle) {
            isListening = false
            microphoneImageView.setImageResource(R.drawable.microphone_not_working_icon)
        }

        override fun onRmsChanged(rmsdB: Float) {

        }
    }

}
