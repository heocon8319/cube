package com.brickmate.cube.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brickmate.cube.R
import com.brickmate.cube.ui.base.BaseFragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.dialog_today_measure_history.*
import kotlinx.android.synthetic.main.dialog_today_summary_calendar.*
import kotlinx.android.synthetic.main.fragment_today_measure_history.*
import kotlinx.android.synthetic.main.fragment_today_measure_history.tvHeight
import kotlinx.android.synthetic.main.fragment_today_measure_history.tvWeight

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val TYPE_GRAPH = "TYPE_GRAPH"

/**
 * A simple [Fragment] subclass.
 * Use the [TodayMeasurehistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodayMeasureHistoryFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var typeGraph: Int? = 0
    private var valueHeight: Float = 80.02f
    private var valueWeight: Float = 12.35f
    private var height: BehaviorSubject<Float> = BehaviorSubject.create()
    private var weight: BehaviorSubject<Float> = BehaviorSubject.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            typeGraph = it.getInt(TYPE_GRAPH)
        }
    }

    override fun layoutId() = R.layout.fragment_today_measure_history

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLineChartHeight()
        initLineChartWeight()
        initListener()
    }

    private fun initLineChart(lineChart: LineChart, xAList: Array<String>, entries: ArrayList<Entry>, upLimit: Float, downLimit: Float) {
        val xAxis: XAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textSize = 12f
        xAxis.mAxisMinimum = 0f
        xAxis.setDrawGridLines(true)
        xAxis.gridLineWidth = 0.5f//vertical
        xAxis.gridColor = resources.getColor(R.color.light_gray)
        val formatter = IndexAxisValueFormatter(xAList)
        xAxis.valueFormatter = formatter

        val ll1 = LimitLine(upLimit, "상위 백분위 5%") //Max
        ll1.lineWidth = 1f
        ll1.enableDashedLine(10f, 10f, 0f)
        ll1.labelPosition = LimitLine.LimitLabelPosition.LEFT_TOP
        ll1.textSize = 10f
        ll1.lineColor = resources.getColor(R.color.jungle_green)
        ll1.textColor = resources.getColor(R.color.fire_bush)

        val ll2 = LimitLine(downLimit, "하위 백분위 5%") //Min
        ll2.lineWidth = 1f
        ll2.enableDashedLine(10f, 10f, 0f)
        ll2.labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM
        ll2.textSize = 10f
        ll2.lineColor = resources.getColor(R.color.jungle_green)
        ll2.textColor = resources.getColor(R.color.fire_bush)

        val leftAxis: YAxis = lineChart.axisLeft
        leftAxis.removeAllLimitLines()
        leftAxis.addLimitLine(ll1)
        leftAxis.addLimitLine(ll2)
        leftAxis.setDrawGridLines(true)
        leftAxis.gridLineWidth = 0.5f//horizontal
        leftAxis.gridColor = resources.getColor(R.color.light_gray)
        leftAxis.textSize = 8f
        leftAxis.setDrawLabels(false)

        val rightAxis: YAxis = lineChart.axisRight
        rightAxis.setDrawLabels(false)
        rightAxis.setDrawGridLines(false)


        var lineDataSet = LineDataSet(entries, "")
        lineDataSet.lineWidth = 1.5f
        lineDataSet.setDrawCircles(false)
        lineDataSet.setColor(resources.getColor(R.color.jungle_green), 255)
        lineDataSet.setDrawValues(false)

        var lineData = LineData(lineDataSet)
        lineChart.data = lineData
        lineChart.setDrawBorders(true)
        lineChart.setBorderWidth(1f)
        lineChart.setBorderColor(resources.getColor(R.color.rolling_stone))
        lineChart.setTouchEnabled(false)
        lineChart.isDragEnabled = false
        lineChart.setScaleEnabled(false)
        lineChart.setPinchZoom(false)
        lineChart.legend.isEnabled = false
        lineChart.description.isEnabled = false
        lineChart.invalidate()
    }

    private fun initLineChartWeight() {
        var entries = ArrayList<Entry>()
        var xAList = arrayOf("")
        var upLimit = 0f
        var downLimit = 0f

        when (typeGraph) {
            0 -> {
                xAList = arrayOf("", "12/1", "12/2", "12/3", "12/4", "12/5", "12/6", "12/7", "")
                upLimit = 10.4f
                downLimit = 10.1f
                entries = getDailyWeightEntries()
            }

            1 -> {
                xAList = arrayOf("", "12/1", "12/8", "12/25", "1/2", "1/9", "1/15", "1/22", "")
                upLimit = 11.3f
                downLimit = 10.7f
                entries = getWeeklyWeightEntries()
            }

            2 -> {
                xAList = arrayOf("", "12/1", "1/1", "2/1", "3/1", "4/1", "5/1", "6/1", "")
                upLimit = 15.6f
                downLimit = 10.4f
                entries = getMonthlyWeightEntries()
            }
        }

        initLineChart(lcWeight, xAList, entries, upLimit, downLimit)
    }

    private fun initLineChartHeight() {
        var entries = ArrayList<Entry>()
        var xAList = arrayOf("")
        var upLimit = 0f
        var downLimit = 0f

        when (typeGraph) {
            0 -> {
                xAList = arrayOf("", "12/1", "12/2", "12/3", "12/4", "12/5", "12/6", "12/7", "")
                upLimit = 70.14f
                downLimit = 70.07f
                entries = getDailyHeightEntries()
            }

            1 -> {
                xAList = arrayOf("", "12/1", "12/8", "12/25", "1/2", "1/9", "1/15", "1/22", "")
                upLimit = 70.22f
                downLimit = 70.15f
                entries = getWeeklyHeightEntries()
            }

            2 -> {
                xAList = arrayOf("", "12/1", "1/1", "2/1", "3/1", "4/1", "5/1", "6/1", "")
                upLimit = 70.37f
                downLimit = 70.17f
                entries = getMonthlyHeightEntries()
            }
        }

        initLineChart(lcHeight, xAList, entries, upLimit, downLimit)
    }

    private fun getDailyHeightEntries(): ArrayList<Entry> {
        val lineEntries = ArrayList<Entry>()
        lineEntries.add(Entry(0f, 70.05f))
        lineEntries.add(Entry(1f, 70.09f))
        lineEntries.add(Entry(2f, 70.1f))
        lineEntries.add(Entry(3f, 70.11f))
        lineEntries.add(Entry(4f, 70.12f))
        lineEntries.add(Entry(5f, 70.13f))
        lineEntries.add(Entry(6f, 70.14f))
        lineEntries.add(Entry(7f, 70.15f))
        lineEntries.add(Entry(8f, 70.16f))
        return lineEntries
    }

    private fun getWeeklyHeightEntries(): ArrayList<Entry> {
        val lineEntries = ArrayList<Entry>()
        lineEntries.add(Entry(0f, 70.06f))
        lineEntries.add(Entry(1f, 70.12f))
        lineEntries.add(Entry(2f, 70.14f))
        lineEntries.add(Entry(3f, 70.16f))
        lineEntries.add(Entry(4f, 70.18f))
        lineEntries.add(Entry(5f, 70.19f))
        lineEntries.add(Entry(6f, 70.23f))
        lineEntries.add(Entry(7f, 70.24f))
        lineEntries.add(Entry(8f, 70.26f))
        return lineEntries
    }

    private fun getMonthlyHeightEntries(): ArrayList<Entry> {
        val lineEntries = ArrayList<Entry>()
        lineEntries.add(Entry(0f, 70.09f))
        lineEntries.add(Entry(1f, 70.12f))
        lineEntries.add(Entry(2f, 70.16f))
        lineEntries.add(Entry(3f, 70.23f))
        lineEntries.add(Entry(4f, 70.28f))
        lineEntries.add(Entry(5f, 70.31f))
        lineEntries.add(Entry(6f, 70.35f))
        lineEntries.add(Entry(7f, 70.39f))
        lineEntries.add(Entry(8f, 70.44f))
        return lineEntries
    }

    private fun getDailyWeightEntries(): ArrayList<Entry> {
        val lineEntries = ArrayList<Entry>()
        lineEntries.add(Entry(0f, 10.0f))
        lineEntries.add(Entry(1f, 10.1f))
        lineEntries.add(Entry(2f, 10.1f))
        lineEntries.add(Entry(3f, 10.1f))
        lineEntries.add(Entry(4f, 10.2f))
        lineEntries.add(Entry(5f, 10.3f))
        lineEntries.add(Entry(6f, 10.3f))
        lineEntries.add(Entry(7f, 10.4f))
        lineEntries.add(Entry(8f, 10.5f))
        return lineEntries
    }

    private fun getWeeklyWeightEntries(): ArrayList<Entry> {
        val lineEntries = ArrayList<Entry>()
        lineEntries.add(Entry(0f, 10.0f))
        lineEntries.add(Entry(1f, 10.1f))
        lineEntries.add(Entry(2f, 10.5f))
        lineEntries.add(Entry(3f, 10.4f))
        lineEntries.add(Entry(4f, 10.7f))
        lineEntries.add(Entry(5f, 11.3f))
        lineEntries.add(Entry(6f, 11.5f))
        lineEntries.add(Entry(7f, 11.8f))
        lineEntries.add(Entry(8f, 12.6f))
        return lineEntries
    }

    private fun getMonthlyWeightEntries(): ArrayList<Entry> {
        val lineEntries = ArrayList<Entry>()
        lineEntries.add(Entry(0f, 10.0f))
        lineEntries.add(Entry(1f, 10.4f))
        lineEntries.add(Entry(2f, 11.5f))
        lineEntries.add(Entry(3f, 12.4f))
        lineEntries.add(Entry(4f, 13.4f))
        lineEntries.add(Entry(5f, 13.9f))
        lineEntries.add(Entry(6f, 14.5f))
        lineEntries.add(Entry(7f, 15.8f))
        lineEntries.add(Entry(8f, 16.6f))
        return lineEntries
    }

    private fun initListener() {
        ivHeightAdd.setOnClickListener {
            valueHeight += 0.01f
            height.onNext(valueHeight)
        }

        ivHeightMinus.setOnClickListener {
            valueHeight -= 0.01f
            height.onNext(valueHeight)
        }

        ivWeightAdd.setOnClickListener {
            valueWeight += 0.01f
            weight.onNext(valueWeight)
        }

        ivWeightMinus.setOnClickListener {
            valueWeight -= 0.01f
            weight.onNext(valueWeight)
        }

        height.subscribe {
            tvHeight.text = String.format(resources.getString(R.string.dialog_measure_history_text_value_height), it)
        }

        weight.subscribe {
            tvWeight.text = String.format(resources.getString(R.string.dialog_measure_history_text_value_weight), it)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TodayMeasurehistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(typeGraph: Int) =
            TodayMeasureHistoryFragment().apply {
                arguments = Bundle().apply {
                    putInt(TYPE_GRAPH, typeGraph)
                }
            }

        fun newInstance(): TodayMeasureHistoryFragment = TodayMeasureHistoryFragment()
    }
}