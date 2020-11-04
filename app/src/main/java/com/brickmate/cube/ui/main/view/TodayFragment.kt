package com.brickmate.cube.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.brickmate.cube.R
import com.brickmate.cube.ui.base.BaseFragment
import com.brickmate.cube.ui.custom.singlerowcalendar.calendar.CalendarChangesObserver
import com.brickmate.cube.ui.custom.singlerowcalendar.calendar.CalendarViewManager
import com.brickmate.cube.ui.custom.singlerowcalendar.calendar.SingleRowCalendarAdapter
import com.brickmate.cube.ui.custom.singlerowcalendar.selection.CalendarSelectionManager
import com.brickmate.cube.ui.custom.singlerowcalendar.utils.DateUtils
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

        val rowCalendar = crRowCalendar.apply {
            calendarViewManager = rowCalendarViewManager
            calendarChangesObserver = rowCalendarChangesObserver
            calendarSelectionManager = rowCalendarSelectionManager
            futureDaysCount = 1000
            pastDaysCount = 500
            includeCurrentDate = true
            init()
        }
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