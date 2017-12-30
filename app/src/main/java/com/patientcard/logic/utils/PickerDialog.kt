
package com.patientcard.logic.utils

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import com.blankj.utilcode.util.ConvertUtils
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.PickerDialogType
import kotlinx.android.synthetic.main.layout_picker.view.*
import org.threeten.bp.LocalTime
import java.text.DateFormatSymbols
import java.util.*


class PickerDialog(context: Context, private val pickerType: PickerDialogType) : FrameLayout(context) {

    private val ANIM_DURATION = 200
    private var pickerLayout: FrameLayout? = null
    private var pickerEditText: EditText? = null

    init {
        View.inflate(context, R.layout.layout_picker, this)
        pickerLayout = this
        this.visibility = View.GONE
        setupPickerContent()
    }

    private fun setupPickerContent() {
        setupWheelVisibility()
        setupWheelValues()
        setupWheelSizes()
        setupBasicValues()
        setupButtons()
    }

    private fun setupWheelVisibility() {
        if (pickerType === PickerDialogType.DATE_PICKER || pickerType === PickerDialogType.TIME_PICKER) {
            firstWheelDivider.visibility = View.VISIBLE
            secondWheelPicker.visibility = View.VISIBLE
        }
        if (pickerType === PickerDialogType.DATE_PICKER) {
            thirdWheelPicker.visibility = View.VISIBLE
        }
    }

    private fun setupWheelValues() {
        setFontInWheelPickers()
        when (pickerType) {
            PickerDialogType.DATE_PICKER -> {
                pickerLabel.text = resources.getString(R.string.set_date)
                firstWheelPicker.data = getNumbersArray(1, 31, 1)
                secondWheelPicker.data = getMonthsArray()
                thirdWheelPicker.data = getNumbersArray(1900, getActualYear(), 1)
//                setupDatePickerListeners()
            }
            PickerDialogType.TIME_PICKER -> {
                pickerLabel.text = resources.getString(R.string.set_hour)
                firstWheelPicker.data = getNumbersArray(0, 23, 1)
                secondWheelPicker.data = getNumbersArray(0, 59, 5)
            }
        }
    }

    private fun setupWheelSizes() {
        when (pickerType) {
            PickerDialogType.DATE_PICKER -> {
                firstWheelDivider.text = "—"
                secondWheelDivider.text = "—"
                firstWheelPicker.layoutParams.width = ConvertUtils.dp2px(50F)
                secondWheelPicker.layoutParams.width = ConvertUtils.dp2px(130F)
                thirdWheelPicker.layoutParams.width = ConvertUtils.dp2px(70F)
            }
            PickerDialogType.TIME_PICKER -> {
                firstWheelDivider.text = ":"
                firstWheelPicker.layoutParams.width = ConvertUtils.dp2px(75F)
                secondWheelPicker.layoutParams.width = ConvertUtils.dp2px(75F)
            }
        }
    }

    private fun setupBasicValues() {
        if (pickerType === PickerDialogType.DATE_PICKER) {
            val currentTime = Calendar.getInstance()
            firstWheelPicker.selectedItemPosition = currentTime.get(Calendar.DAY_OF_MONTH)
            secondWheelPicker.selectedItemPosition = currentTime.get(Calendar.MONTH)
            thirdWheelPicker.selectedItemPosition = currentTime.get(Calendar.YEAR)
        } else if (pickerType === PickerDialogType.TIME_PICKER) {
            val currentTime = LocalTime.now()
            firstWheelPicker.selectedItemPosition = currentTime.hour
            secondWheelPicker.selectedItemPosition = currentTime.minute / 5
        }
    }

    private fun setupButtons() {
        setTimeButton.setOnClickListener {
            if (pickerEditText != null) {
                pickerEditText?.setText(getPickerResult())
            }
            AnimUtils.fadeOut(ANIM_DURATION, pickerLayout!!)
        }
        cancelTimeButton.setOnClickListener {
            AnimUtils.fadeOut(ANIM_DURATION, pickerLayout!!)
        }
    }

    private fun getPickerResult(): String? {
        val firstWheelPosition = firstWheelPicker.currentItemPosition
        val secondWheelPosition = secondWheelPicker.currentItemPosition
        val thirdWheelPosition = thirdWheelPicker.currentItemPosition
        return when (pickerType) {
            PickerDialogType.DATE_PICKER -> {
                val day = getNumbersArray(1, 31, 1)[firstWheelPosition]
                val month = if (secondWheelPosition < 9) "0" + (secondWheelPosition + 1) else "" + (secondWheelPosition + 1)
                val year = getNumbersArray(1900, getActualYear(), 1)[thirdWheelPosition]
                "$day.$month.$year"
            }
            PickerDialogType.TIME_PICKER -> {
                val hour = getNumbersArray(0, 23, 1)[firstWheelPosition]
                val minute = getNumbersArray(0, 59, 5)[secondWheelPosition]
                hour + ":" + minute
            }
        }
    }

    private fun setFontInWheelPickers() {
        val tf = Typeface.createFromAsset(context.assets, "fonts/TitilliumWeb-Light.ttf")
        firstWheelPicker.typeface = tf
        secondWheelPicker.typeface = tf
        thirdWheelPicker.typeface = tf
    }

    private fun getNumbersArray(minValue: Int, maxValue: Int, step: Int): ArrayList<String> {
        val timeValues = ArrayList<String>()
        var i = minValue
        while (i < maxValue + 1) {
            if (i < 10) {
                timeValues.add("0" + i)
            } else {
                timeValues.add("" + i)
            }
            i += step
        }
        return timeValues
    }

    private fun getMonthsArray(): ArrayList<String> {
        val slots = ArrayList<String>()
        val current = resources.configuration.locale
        val symbols = DateFormatSymbols(current)
        slots.addAll(Arrays.asList(*symbols.months))
        return slots
    }

    private fun getActualYear(): Int {
        val currentTime = Calendar.getInstance()
        return currentTime.get(Calendar.YEAR)
    }

    fun setPickerEditText(editText: EditText) {
        pickerEditText = editText
        val editTextValue = editText.text.toString()
        setPickerValue(editTextValue)
    }

    private fun setPickerValue(editTextValue: String) {
//        if (editTextValue != "") {
//            when (pickerType) {
//                PickerDialogType.TIME_PICKER -> {
//                    val time = LocalTime.parse(editTextValue)
//                    firstWheelPicker.selectedItemPosition = time.hour
//                    secondWheelPicker.selectedItemPosition = time.minute
//                }
//            }
//        }
    }

}
