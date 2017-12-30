package com.patientcard.views.addrecommendation

import android.content.Intent
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RelativeLayout
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.PickerDialogType
import com.patientcard.logic.utils.AnimUtils
import com.patientcard.logic.utils.PickerDialog
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import com.patientcard.views.recommendations.RecommendationsActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_add_recommendation.*

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
        editText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showPicker(editText)
                editText.isCursorVisible = false
                topAccentView.requestFocus()
            }
        }
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

    private fun setupSaveRecommendationClick() {
        checkFab.setOnClickListener {
            startActivity(Intent(this, RecommendationsActivity::class.java))
            finish()
        }
    }

    fun getPickerLayoutParams(): RelativeLayout.LayoutParams {
        val layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        return layoutParams
    }

}
