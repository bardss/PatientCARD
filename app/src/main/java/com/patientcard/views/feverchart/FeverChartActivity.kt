package com.patientcard.views.feverchart

import com.jjoe64.graphview.helper.StaticLabelsFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.patientcard.R
import com.patientcard.logic.model.transportobjects.FeverCardDTO
import com.patientcard.logic.utils.ResUtil
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_fever_chart.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@ActivityView(layout = R.layout.activity_fever_chart, presenter = FeverChartPresenterImpl::class)
class FeverChartActivity : BaseActivity(), FeverChartView {

    @Presenter
    lateinit var presenter: FeverChartPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun setupFeverGraph(feverCard: ArrayList<FeverCardDTO>?) {
        val pulsePoints = arrayOfNulls<DataPoint>(feverCard?.size!!)
        for (i in 0 until feverCard.size) {
            pulsePoints[feverCard.size-(i+1)] = DataPoint(feverCard.size-(i+1).toDouble(), feverCard[i].pulse.toDouble())
        }
        val seriesPulse = LineGraphSeries<DataPoint>(pulsePoints)

        val temperaturePoints = arrayOfNulls<DataPoint>(feverCard.size)
        for (i in 0 until feverCard.size) {
            temperaturePoints[feverCard.size-(i+1)] = DataPoint(feverCard.size-(i+1).toDouble(), feverCard[i].temperature.toDouble())
        }
        val seriesTemperature = LineGraphSeries<DataPoint>(temperaturePoints)

        feverGraphView.addSeries(seriesTemperature)
        feverGraphView.secondScale.addSeries(seriesPulse)
        feverGraphView.secondScale.setMinY(60.0)
        feverGraphView.secondScale.setMaxY(135.0)
        feverGraphView.setBackgroundColor(ResUtil.getColor(R.color.colorPrimary)!!)

        seriesTemperature.color = ResUtil.getColor(R.color.colorPulse)!!
        seriesTemperature.title = ResUtil.getString(R.string.pulse)
        seriesTemperature.isDrawDataPoints = true
        seriesTemperature.thickness = 8

        seriesPulse.color = ResUtil.getColor(R.color.colorTemperature)!!
        seriesPulse.title = ResUtil.getString(R.string.temperature)
        seriesPulse.isDrawDataPoints = true
        seriesPulse.thickness = 8
        feverGraphView.gridLabelRenderer.verticalLabelsSecondScaleColor = ResUtil.getColor(R.color.colorTemperature)!!

        val staticLabelsFormatter = StaticLabelsFormatter(feverGraphView)
        val labels = arrayOfNulls<String>(feverCard.size)
        for (i in 0 until feverCard.size) {
            labels[feverCard.size-(i+1)] = getFormattedDate(feverCard[i].date) + "\n" + feverCard[i].timeOfDay.stringValue
        }
        staticLabelsFormatter.setHorizontalLabels(labels)
        feverGraphView.gridLabelRenderer.labelFormatter = staticLabelsFormatter
    }

    fun getFormattedDate(date: LocalDate?): String? {
        return if (date != null) {
            val dtf = DateTimeFormatter.ofPattern("dd.MM")
            date.format(dtf)
        } else {
            ""
        }
    }
}