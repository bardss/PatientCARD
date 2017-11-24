package com.patientcard.feverchart

import android.graphics.Color
import android.view.WindowManager
import com.github.mikephil.charting.charts.CombinedChart.DrawOrder
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.patientcard.R
import com.patientcard.base.BaseActivity
import com.patientcard.base.BasePresenter
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_fever_chart.*
import java.util.*


@ActivityView(layout = R.layout.activity_fever_chart, presenter = FeverChartPresenterImpl::class)
class FeverChartActivity : BaseActivity(), FeverChartView {

    var entries: List<Entry> = ArrayList()


    @Presenter
    lateinit var presenter: FeverChartPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        setupChart()
    }

    private fun setupChart() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

        feverLineChart.description.isEnabled = false
        feverLineChart.setBackgroundColor(Color.WHITE)
        feverLineChart.setDrawGridBackground(false)
        feverLineChart.setDrawBarShadow(false)
        feverLineChart.isHighlightFullBarEnabled = false

        // draw bars behind lines
        feverLineChart.drawOrder = arrayOf(DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.CANDLE, DrawOrder.LINE, DrawOrder.SCATTER)

        val l = feverLineChart.legend
        l.isWordWrapEnabled = true
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)

        val rightAxis = feverLineChart.axisRight
        rightAxis.setDrawGridLines(false)
        rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        val leftAxis = feverLineChart.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        val xAxis = feverLineChart.xAxis
        xAxis.position = XAxisPosition.BOTH_SIDED
        xAxis.axisMinimum = 0f
        xAxis.granularity = 1f
//        xAxis.setValueFormatter({ value, axis -> mMonths[value.toInt() % mMonths.length] })

        val data = CombinedData()

        data.setData(generateLineData())
        data.setData(generateLineData2())
//        data.setValueTypeface(mTfLight)

        xAxis.axisMaximum = data.xMax + 0.25f

        feverLineChart.data = data
        feverLineChart.invalidate()
    }

    private fun generateLineData(): LineData {
        val d = LineData()

        val entries = ArrayList<Entry>()

        for (index in 0 until 15)
            entries.add(Entry(index + 0.5f, rand(5, 20).toFloat()))

        val set = LineDataSet(entries, "Line DataSet")
        set.color = Color.rgb(240, 238, 70)
        set.lineWidth = 2.5f
        set.setCircleColor(Color.rgb(240, 238, 70))
        set.circleRadius = 5f
        set.fillColor = Color.rgb(240, 238, 70)
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.setDrawValues(true)
        set.valueTextSize = 10f
        set.valueTextColor = Color.rgb(240, 238, 70)

        set.axisDependency = YAxis.AxisDependency.LEFT
        d.addDataSet(set)

        return d
    }

    private fun generateLineData2(): LineData {
        val d = LineData()

        val entries = ArrayList<Entry>()

        for (index in 0 until 15)
            entries.add(Entry(index + 0.5f, rand(80, 100).toFloat()))

        val set = LineDataSet(entries, "Line DataSet2")
        set.color = Color.rgb(240, 210, 70)
        set.lineWidth = 2.5f
        set.setCircleColor(Color.rgb(240, 238, 70))
        set.circleRadius = 5f
        set.fillColor = Color.rgb(240, 238, 70)
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.setDrawValues(true)
        set.valueTextSize = 10f
        set.valueTextColor = Color.rgb(240, 238, 70)

        set.axisDependency = YAxis.AxisDependency.LEFT
        d.addDataSet(set)

        return d
    }

    fun rand(from: Int, to: Int) : Int {
        val random = Random()
        return random.nextInt(to - from) + from
    }


}
