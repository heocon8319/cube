package com.brickmate.cube.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.brickmate.cube.R
import com.brickmate.cube.ui.base.BaseFragment
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.android.synthetic.main.fragment_today_nutri_summary.*
import kotlinx.android.synthetic.main.layout_today_process_bar.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TodayNutriSummaryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodayNutriSummaryFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun layoutId() = R.layout.fragment_today_nutri_summary

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initProcessBar()
        initBarChart()
    }

    private fun initProcessBar() {
        clProcessBar.pbCalorie.progress = 30
        clProcessBar.tvCalorie.text = "30/100"
        clProcessBar.pbCarbon.progress = 70
        clProcessBar.tvCarbon.text = "70/100"
        clProcessBar.pbProtein.progress = 50
        clProcessBar.tvProtein.text = "50/100"
        clProcessBar.pbFat.progress = 40
        clProcessBar.tvFat.text = "40/100"
        clProcessBar.pbWater.progress = 60
        clProcessBar.tvWater.text = "60/100"
    }

    private fun initBarChart() {
        initVitaminBarChart()
        initMineralBarChart()
    }

    private fun initVitaminBarChart(){
        val barDataSet = BarDataSet(getDataVitamin(), "")
        barDataSet.setDrawValues(false);
        barDataSet.setColor(resources.getColor(R.color.fire_bush), 255)

        val barData = BarData(barDataSet)
        barData.barWidth = 0.2f

        //Line 100%
        val ll1 = LimitLine(100f, "100%")
        ll1.lineWidth = 0.8f
        ll1.enableDashedLine(10f, 10f, 0f)
        ll1.labelPosition = LimitLabelPosition.RIGHT_TOP
        ll1.lineColor = resources.getColor(R.color.jungle_green)
        ll1.textSize = 10f

        //X
        val xAxis = bcVitamin.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.axisLineWidth = 0.8f
        xAxis.axisLineColor = resources.getColor(R.color.fire_bush)
        val months = arrayOf("A", "B6", "B12", "C", "D", "E", "K", "")
        val formatter = IndexAxisValueFormatter(months)
        xAxis.valueFormatter = formatter

        //Y
        val leftAxis: YAxis = bcVitamin.axisLeft
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(ll1)
        leftAxis.setDrawGridLines(false)
        leftAxis.setDrawAxisLine(false)
        leftAxis.isEnabled = true
        leftAxis.setDrawLabels(false)

        //Start zero
        bcVitamin.axisLeft.axisMinimum = 0f;
        bcVitamin.axisRight.axisMinimum = 0f;

        bcVitamin.axisRight.isEnabled = false

        // add data
        bcVitamin.data = barData
        bcVitamin.legend.isEnabled = false
        bcVitamin.description.isEnabled = false
        bcVitamin.setFitBars(true);
        bcVitamin.invalidate();
    }

    private fun initMineralBarChart(){
        val barDataSet = BarDataSet(getDataMineral(), "")
        barDataSet.setDrawValues(false);
        barDataSet.setColor(resources.getColor(R.color.fire_bush), 255)

        val barData = BarData(barDataSet)
        barData.barWidth = 0.2f

        //Line 100%
        val ll1 = LimitLine(60f, "100%")
        ll1.lineWidth = 0.5f
        ll1.enableDashedLine(10f, 10f, 0f)
        ll1.labelPosition = LimitLabelPosition.RIGHT_TOP
        ll1.lineColor = resources.getColor(R.color.jungle_green)
        ll1.textSize = 10f

        //X
        val xAxis = bcMineral.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.axisLineColor = resources.getColor(R.color.fire_bush)
        val months = arrayOf("철분", "칼슘", "칼륨", "마그네슘C", "나트륨", "식이섬유", "")
        val formatter = IndexAxisValueFormatter(months)
        xAxis.valueFormatter = formatter

        //Y
        val leftAxis: YAxis = bcMineral.axisLeft
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(ll1)
        leftAxis.setDrawGridLines(false)
        leftAxis.setDrawAxisLine(false)
        leftAxis.isEnabled = true
        leftAxis.setDrawLabels(false)

        //Start zero
        bcMineral.axisLeft.axisMinimum = 0f;
        bcMineral.axisRight.axisMinimum = 0f;

        bcMineral.axisRight.isEnabled = false

        // add data
        bcMineral.data = barData
        bcMineral.legend.isEnabled = false
        bcMineral.description.isEnabled = false
        bcMineral.setFitBars(true);
        bcMineral.invalidate();
    }

    private fun getDataVitamin(): ArrayList<BarEntry>? {
        val entries: ArrayList<BarEntry> = ArrayList()
        entries.add(BarEntry(0f, 80f))
        entries.add(BarEntry(1f, 110f))
        entries.add(BarEntry(2f, 100f))
        entries.add(BarEntry(3f, 90f))
        entries.add(BarEntry(4f, 110f))
        entries.add(BarEntry(5f, 110f))
        entries.add(BarEntry(6f, 110f))
        entries.add(BarEntry(7f, 0f))
        return entries
    }

    private fun getDataMineral(): ArrayList<BarEntry>? {
        val entries: ArrayList<BarEntry> = ArrayList()
        entries.add(BarEntry(0f, 30f))
        entries.add(BarEntry(1f, 20f))
        entries.add(BarEntry(2f, 56f))
        entries.add(BarEntry(3f, 15f))
        entries.add(BarEntry(4f, 40f))
        entries.add(BarEntry(5f, 70f))
        entries.add(BarEntry(6f, 0f))
        return entries
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TodayNutriSummaryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TodayNutriSummaryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        fun newInstance(): TodayNutriSummaryFragment = TodayNutriSummaryFragment()
    }
}