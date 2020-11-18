package com.brickmate.cube.ui.main.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentPagerAdapter
import com.brickmate.cube.R
import com.brickmate.cube.ui.main.adapter.TodayMeasureHistoryAdapter
import com.brickmate.cube.ui.main.view.TodayMeasureHistoryFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.dialog_today_measure_history.*


class TodayMeasureHistoryDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_today_measure_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTitle.text = resources.getString(R.string.dialog_measure_history_text_growth_record)

        val adapter =
            TodayMeasureHistoryAdapter(childFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, this.requireContext())
        adapter.addFragment("", TodayMeasureHistoryFragment.newInstance(0))
        adapter.addFragment("", TodayMeasureHistoryFragment.newInstance(1))
        adapter.addFragment("", TodayMeasureHistoryFragment.newInstance(2))

        vpGraph.adapter = adapter
        tlGraph.setupWithViewPager(vpGraph)

        for (i in 0 until tlGraph.tabCount) {
            val tab: TabLayout.Tab? = tlGraph.getTabAt(i)
            tab?.customView = adapter.getTabView(i)
        }
        adapter.onSelectedView(tlGraph, 0)

        initListener(adapter)
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
    }

    private fun initListener(adapter: TodayMeasureHistoryAdapter) {
        tlGraph.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val customTab = tab.position
                adapter.onSelectedView(tlGraph, customTab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val customTab = tab.position
                adapter.onUnselectedView(tlGraph, customTab)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    companion object {
        fun newInstance(): TodayMeasureHistoryDialog = TodayMeasureHistoryDialog()
    }
}