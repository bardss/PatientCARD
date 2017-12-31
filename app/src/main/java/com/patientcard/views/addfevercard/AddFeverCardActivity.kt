package com.patientcard.views.addfevercard

import android.content.Intent
import android.widget.EditText
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.FeverCardValueType
import com.patientcard.logic.model.transportobjects.FeverCardDTO
import com.patientcard.logic.model.transportobjects.TimeOfDay
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import com.patientcard.views.shortfever.ShortFeverActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_add_fevercard.*
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

@ActivityView(layout = R.layout.activity_add_fevercard, presenter = AddFeverCardPresenterImpl::class)
class AddFeverCardActivity : BaseActivity(), AddFeverCardView {

    @Presenter
    lateinit var presenter: AddFeverCardPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun setupButton() {
        checkFab.setOnClickListener {
            val validData: Boolean = isValueValid(pulseEditText, FeverCardValueType.PULSE) && isValueValid(temperatureEditText, FeverCardValueType.TEMPERATURE)
            if (validData) {
                presenter.addFeverCard(getFeverCard())
            }
        }
    }

    private fun getFeverCard(): FeverCardDTO {
        val timeOfDay = if (LocalTime.now().hour < 15) TimeOfDay.MORNING else TimeOfDay.EVENING
        val pulse = pulseEditText.text.toString().toInt()
        val temperature = temperatureEditText.text.toString().toInt()
        return FeverCardDTO(null, null, LocalDate.now(), timeOfDay, pulse, temperature)
    }

    private fun isValueValid(editText: EditText, valueType: FeverCardValueType): Boolean {
        if (editText.text.isEmpty()) {
            editText.error = ResUtil.getString(R.string.fill_data)
            return false
        }
        val intValue = editText.text.toString().toInt()
        if (valueType == FeverCardValueType.PULSE && intValue < 55) {
            editText.error = ResUtil.getString(R.string.pulse_have_to_be_more)
            return false
        } else if (valueType == FeverCardValueType.PULSE && intValue > 165) {
            editText.error = ResUtil.getString(R.string.pulse_have_to_be_below)
            return false
        } else if (valueType == FeverCardValueType.TEMPERATURE && intValue < 30) {
            editText.error = ResUtil.getString(R.string.temperature_have_to_be_more)
            return false
        } else if (valueType == FeverCardValueType.TEMPERATURE && intValue > 50) {
            editText.error = ResUtil.getString(R.string.temperature_have_to_be_below)
            return false
        }
        return true
    }

    override fun setPatientName(patientName: String?) {
        nameTextView.text = patientName
    }

    override fun navigateBack() {
        startActivity(Intent(this, ShortFeverActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
    }
}