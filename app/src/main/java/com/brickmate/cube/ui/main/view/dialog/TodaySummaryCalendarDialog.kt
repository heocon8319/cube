package com.brickmate.cube.ui.main.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.brickmate.cube.R
import com.brickmate.cube.ui.custom.singlerowcalendar.utils.DateUtils
import kotlinx.android.synthetic.main.dialog_today_summary_calendar.*
import kotlinx.android.synthetic.main.fragment_today.*
import kotlinx.android.synthetic.main.layout_today_radar_graph.view.*
import java.util.*

class TodaySummaryCalendarDialog : DialogFragment() {

    private var dateSelected = Date()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_today_summary_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
        val calendar = DateUtils.toCalendar(dateSelected)
        calendar?.let { cvCustomCalendar.setCalendar(it) }

        tvHeight.text = String.format(resources.getString(R.string.dialog_calendar_text_value_height), "+", 5f)
        tvWeight.text = String.format(resources.getString(R.string.dialog_calendar_text_value_weight), "+", 0.8f)
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun initListener() {
        tvClose.setOnClickListener {
            dismiss()
        }

        cvCustomCalendar.getListDateSelected().observe(this, { date ->
            tvCountDay.text = date.size.toString()
        })
    }

    fun setCalendar(date: Date) {
        dateSelected = date
    }

    companion object {
        fun newInstance(): TodaySummaryCalendarDialog = TodaySummaryCalendarDialog()
    }
}