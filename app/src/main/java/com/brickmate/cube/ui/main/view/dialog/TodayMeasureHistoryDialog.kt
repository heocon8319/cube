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
import kotlinx.android.synthetic.main.dialog_today_measure_history.*

class TodayMeasureHistoryDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_today_measure_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TodayMeasureHistoryAdapter(childFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        adapter.addFragment("Boy", TodayMeasureHistoryFragment.newInstance(0))
        adapter.addFragment("Girl", TodayMeasureHistoryFragment.newInstance(1))
        adapter.addFragment("Robot", TodayMeasureHistoryFragment.newInstance(2))
        vpGraph.adapter = adapter
        tlGraph.setupWithViewPager(vpGraph)
        tvTitle.text = resources.getString(R.string.dialog_measure_history_text_growth_record)
    }

    override fun onResume() {
        super.onResume()
//        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
//        params.width = ViewGroup.LayoutParams.MATCH_PARENT
//        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
//        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
    }

    companion object {
        fun newInstance(): TodayMeasureHistoryDialog = TodayMeasureHistoryDialog()
    }
}