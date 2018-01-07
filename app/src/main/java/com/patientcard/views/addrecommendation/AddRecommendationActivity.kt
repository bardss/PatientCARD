package com.patientcard.views.addrecommendation

import android.content.Intent
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RelativeLayout
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.PickerDialogType
import com.patientcard.logic.model.transportobjects.RecommendationDTO
import com.patientcard.logic.utils.AnimUtils
import com.patientcard.logic.utils.FormatTimeDateUtil
import com.patientcard.logic.utils.PickerDialog
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import com.patientcard.views.recommendations.RecommendationsActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_add_recommendation.*
import kotlinx.android.synthetic.main.dialog_delete.*
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

@ActivityView(layout = R.layout.activity_add_recommendation, presenter = AddRecommendationPresenterImpl::class)
class AddRecommendationActivity : BaseActivity(), AddRecommendationView {

    @Presenter
    lateinit var presenter: AddRecommendationPresenter

    private var timePickerDialog: PickerDialog? = null

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        setupSaveRecommendationClick()
        setupPickers()
    }

    override fun setPatientName(patientName: String?) {
        nameTextView.text = patientName
    }

    override fun setTitle(title: String?) {
        pageTitleTextView.text = title
    }

    override fun setLabel() {
        recommendationDateTextView.text = ResUtil.getString(R.string.recommendation) + " " + FormatTimeDateUtil.getFormattedDate(LocalDate.now())
    }

    override fun setupDeleteIcon(recommendation: RecommendationDTO?) {
        deleteImageView.visibility = View.VISIBLE
        deleteImageView.setOnClickListener {
            AnimUtils.fadeIn(300, deleteDialogLayout)
            whatToDeleteTextView.text = ResUtil.getString(R.string.recommendation)
            whenToDeleteTextView.text = FormatTimeDateUtil.getFormattedDate(recommendation?.date)
            setupDeleteDialogButtons()
        }
    }

    private fun setupDeleteDialogButtons() {
        cancelButton.setOnClickListener {
            AnimUtils.fadeOut(300, deleteDialogLayout)
        }
        deleteButton.setOnClickListener {
            presenter.deleteRecommendation()
        }
    }

    override fun fillFields(recommendation: RecommendationDTO?) {
        recommendationDateTextView.text = ResUtil.getString(R.string.recommendation) + " " + FormatTimeDateUtil.getFormattedDate(recommendation?.date)
        drugEditText.setText(recommendation?.description)
        morningTimeEditText.setText(FormatTimeDateUtil.getFormattedTime(recommendation?.morning))
        noonTimeEditText.setText(FormatTimeDateUtil.getFormattedTime(recommendation?.noon))
        eveningTimeEditText.setText(FormatTimeDateUtil.getFormattedTime(recommendation?.evening))
        nightTimeEditText.setText(FormatTimeDateUtil.getFormattedTime(recommendation?.night))
    }

    private fun setupSaveRecommendationClick() {
        checkFab.setOnClickListener {
            val validData = isEditTextEmpty(drugEditText)
            if (validData) presenter.saveRecommendation(getRecommendation())
        }
    }

    private fun getRecommendation(): RecommendationDTO {
        val note = drugEditText.text.toString()
        val morning: LocalTime? = presenter.getLocalTimeFromString(morningTimeEditText.text.toString())
        val noon: LocalTime? = presenter.getLocalTimeFromString(noonTimeEditText.text.toString())
        val evening: LocalTime? = presenter.getLocalTimeFromString(eveningTimeEditText.text.toString())
        val night: LocalTime? = presenter.getLocalTimeFromString(nightTimeEditText.text.toString())
        return RecommendationDTO(null, null, LocalDate.now(), note, morning, noon, evening, night)
    }

    override fun navigateBack() {
        startActivity(Intent(this, RecommendationsActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
    }

    private fun setupPickers() {
        timePickerDialog = PickerDialog(this, PickerDialogType.TIME_PICKER)
        setupPickerDialog(timePickerDialog)
        setupEditTextPickers(morningTimeEditText)
        setupEditTextPickers(noonTimeEditText)
        setupEditTextPickers(eveningTimeEditText)
        setupEditTextPickers(nightTimeEditText)
    }

    private fun setupEditTextPickers(editText: EditText) {
        editText.isFocusable = true
        editText.isFocusableInTouchMode = true
        editText.isClickable = false
        editText.inputType = InputType.TYPE_NULL
        editText.onFocusChangeListener = getTimePickerListener(editText)
    }

    private fun getTimePickerListener(editText: EditText): View.OnFocusChangeListener {
        return View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showPicker(editText)
                editText.isCursorVisible = false
                topAccentView.requestFocus()
            }
        }
    }

    private fun showPicker(editText: EditText) {
        timePickerDialog?.setPickerEditText(editText)
        AnimUtils.fadeIn(200, timePickerDialog!!)
        timePickerDialog?.bringToFront()
        timePickerDialog?.requestLayout()
    }

    private fun setupPickerDialog(picker: PickerDialog?) {
        val layoutParams = getPickerLayoutParams()
        picker?.layoutParams = layoutParams
        mainRelativeLayout.addView(picker)
        picker?.bringToFront()
    }

    private fun getPickerLayoutParams(): RelativeLayout.LayoutParams {
        val layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        return layoutParams
    }

    private fun isEditTextEmpty(editText: EditText): Boolean{
        if (editText.text.isEmpty()) {
            editText.error = ResUtil.getString(R.string.fill_data)
            return false
        }
        return true
    }

}
