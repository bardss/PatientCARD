package com.patientcard.feverchart

import com.jjoe64.graphview.helper.StaticLabelsFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.patientcard.R
import com.patientcard.base.BaseActivity
import com.patientcard.base.BasePresenter
import com.patientcard.utils.ResUtil
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_fever_chart.*




@ActivityView(layout = R.layout.activity_fever_chart, presenter = FeverChartPresenterImpl::class)
class FeverChartActivity : BaseActivity(), FeverChartView {

    @Presenter
    lateinit var presenter: FeverChartPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        setupFeverGraph()
    }

    private fun setupFeverGraph() {
        val series = LineGraphSeries<DataPoint>(arrayOf<DataPoint>(
                DataPoint(0.0, 1.0),
                DataPoint(1.0, 5.0),
                DataPoint(2.0, 3.0),
                DataPoint(3.0, 2.0),
                DataPoint(4.0, 6.0)
        ))

        val series2 = LineGraphSeries<DataPoint>(arrayOf<DataPoint>(
                DataPoint(0.0, 10.0),
                DataPoint(1.0, 50.0),
                DataPoint(2.0, 90.0),
                DataPoint(3.0, 20.0),
                DataPoint(4.0, 10.0)
        ))

        feverGraphView.addSeries(series)
        feverGraphView.secondScale.addSeries(series2)
        feverGraphView.secondScale.setMinY(0.0)
        feverGraphView.secondScale.setMaxY(100.0)
        feverGraphView.setBackgroundColor(ResUtil.getColor(R.color.colorPrimary)!!)

        series.color = ResUtil.getColor(R.color.colorPulse)!!
        series.title = "Pulse"
        series.isDrawDataPoints = true
        series.thickness = 8


        series2.color = ResUtil.getColor(R.color.colorTemperature)!!
        series2.title = "Temperature"
        series2.isDrawDataPoints = true
        series2.thickness = 8
        feverGraphView.gridLabelRenderer.verticalLabelsSecondScaleColor = ResUtil.getColor(R.color.colorTemperature)!!

        val staticLabelsFormatter = StaticLabelsFormatter(feverGraphView)
        staticLabelsFormatter.setHorizontalLabels(arrayOf("Rano\n10.11.2017", "Wieczor\n10.11.2017", "Rano\n11.11.2017", "Wieczor\n11.11.2017", "Rano\n12.11.2017"))
        feverGraphView.gridLabelRenderer.labelFormatter = staticLabelsFormatter

    }

}

