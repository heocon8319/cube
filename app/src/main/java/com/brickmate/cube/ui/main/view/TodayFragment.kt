package com.brickmate.cube.ui.main.view

import android.os.Bundle
import android.util.SparseIntArray
import androidx.fragment.app.Fragment
import com.brickmate.cube.R
import com.brickmate.cube.ui.base.BaseFragment
import com.brickmate.cube.ui.custom.singlerowcalendar.calendar.CalendarChangesObserver
import com.brickmate.cube.ui.custom.singlerowcalendar.calendar.CalendarViewManager
import com.brickmate.cube.ui.custom.singlerowcalendar.calendar.SingleRowCalendarAdapter
import com.brickmate.cube.ui.custom.singlerowcalendar.selection.CalendarSelectionManager
import com.brickmate.cube.ui.custom.singlerowcalendar.utils.DateUtils
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import kotlinx.android.synthetic.main.fragment_today.*
import kotlinx.android.synthetic.main.layout_item_row_calendar.view.*
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TodayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodayFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val calendar = Calendar.getInstance()
    private var currentMonth = 0

    private val scores = SparseIntArray(5)
    private val entries: ArrayList<RadarEntry> = ArrayList()
    private val dataSets: ArrayList<IRadarDataSet> = ArrayList()


    override fun layoutId() = R.layout.fragment_today

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRowCalendar()
        initChart()
    }

    private fun initRowCalendar() {
        // Set current date, month
        calendar.time = Date()
        currentMonth = calendar[Calendar.MONTH]
        tvMonth.text = currentMonth.toString()

        // Layout
        val rowCalendarViewManager = object :
            CalendarViewManager {
            override fun bindDataToCalendarView(
                holder: SingleRowCalendarAdapter.CalendarViewHolder,
                date: Date,
                position: Int,
                isSelected: Boolean
            ) {
                // Item
                holder.itemView.tvDate.text = DateUtils.getDayNumber(date)
                holder.itemView.tvDay.text = DateUtils.getDay3LettersName(date)
            }

            override fun setCalendarViewResourceId(
                position: Int,
                date: Date,
                isSelected: Boolean
            ): Int {
                // Set date
                val cal = Calendar.getInstance()
                cal.time = date
                // Return layout when selected
                return if (isSelected)
                    R.layout.layout_item_row_calendar_selected
                else
                    R.layout.layout_item_row_calendar

            }
        }

        val rowCalendarChangesObserver = object :
            CalendarChangesObserver {
            override fun whenSelectionChanged(isSelected: Boolean, position: Int, date: Date) {
                tvMonth.text = DateUtils.getMonthNumber(date)
                super.whenSelectionChanged(isSelected, position, date)
            }
        }

        val rowCalendarSelectionManager = object : CalendarSelectionManager {
            override fun canBeItemSelected(position: Int, date: Date): Boolean {
                return true
            }
        }

        crRowCalendar.apply {
            calendarViewManager = rowCalendarViewManager
            calendarChangesObserver = rowCalendarChangesObserver
            calendarSelectionManager = rowCalendarSelectionManager
            futureDaysCount = 500
            pastDaysCount = 500
            includeCurrentDate = true
            init()
        }
    }

    private fun initChart() {

        val xAxisValues: List<String> = ArrayList(
            Arrays.asList(
                resources.getString(R.string.screen_today_text_calorie),
                resources.getString(R.string.screen_today_text_fat),
                resources.getString(R.string.screen_today_text_protein),
                resources.getString(R.string.screen_today_text_carbon),
                resources.getString(R.string.screen_today_text_iron)
            )
        )

        val xAxis = mChart!!.xAxis
        xAxis.xOffset = 0f
        xAxis.yOffset = 0f
        xAxis.textSize = 13f
        xAxis.setCenterAxisLabels(true);
        xAxis.valueFormatter = (IndexAxisValueFormatter(xAxisValues))

        val yAxis = mChart!!.yAxis
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 50f
        yAxis.setDrawLabels(false)


        mChart!!.legend.isEnabled = false
        mChart!!.description.isEnabled = false

        scores.append(1, 15);
        scores.append(2, 25);
        scores.append(3, 35);
        scores.append(4, 40);
        scores.append(5, 45);

        mChart.webColorInner = resources.getColor(R.color.golden_yellow)
        mChart.webColor = resources.getColor(R.color.jungle_green)
        mChart.webLineWidth = 3f

        drawChart();
    }

    private fun drawChart() {
        entries.clear()
        for (i in 1..5) {//1..5
            entries.add(RadarEntry(scores[i].toFloat()))
        }
        val dataSet = RadarDataSet(entries, "")
        dataSet.valueTextColor = resources.getColor(R.color.transparent)

        dataSet.fillAlpha = 255
        dataSet.fillColor = resources.getColor(R.color.jungle_green)
        dataSet.setDrawFilled(true)

        dataSets.add(dataSet)
        val data = RadarData(dataSets)
        data.setValueTextSize(8f)
        mChart!!.data = data
        mChart.invalidate()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TodayFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TodayFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        fun newInstance(): TodayFragment = TodayFragment()
    }
}