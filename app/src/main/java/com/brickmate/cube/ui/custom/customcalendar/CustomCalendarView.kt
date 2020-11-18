package com.brickmate.cube.ui.custom.customcalendar

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brickmate.cube.R
import kotlinx.android.synthetic.main.layout_custom_calendar.view.*
import kotlinx.android.synthetic.main.layout_item_custom_calendar.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CalendarView : LinearLayout {
    // date format
    private var dateFormat: String? = null

    // current displayed month
    private var currentDate: Calendar = Calendar.getInstance()

    // list date is selected
    private var listSelected = ArrayList<Date>()
    private var listUpdate = MutableLiveData<ArrayList<Date>>()

    constructor(context: Context?) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initControl(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initControl(context, attrs)
    }

    fun setCalendar(calendar : Calendar){
        currentDate = calendar
        updateCalendar()
    }

    fun getListDateSelected() : MutableLiveData<ArrayList<Date>>{
        return listUpdate
    }

    /**
     * Load control xml layout
     */
    private fun initControl(context: Context, attrs: AttributeSet?) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_custom_calendar, this)

        loadDateFormat(attrs)
        assignClickHandlers()
        updateCalendar()
    }

    private fun loadDateFormat(attrs: AttributeSet?) {
        val ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCalendarView)
        try {
            // try to load provided date format, and fallback to default otherwise
            dateFormat = ta.getString(R.styleable.CustomCalendarView_dateFormat)
            if (dateFormat == null) dateFormat = DATE_FORMAT
        } finally {
            ta.recycle()
        }
    }

    private fun assignClickHandlers() {
        // add one month and refresh UI
        btnNext?.setOnClickListener(OnClickListener {
            currentDate.add(Calendar.MONTH, 1)
            updateCalendar()
        })

        // subtract one month and refresh UI
        btnBack?.setOnClickListener(OnClickListener {
            currentDate.add(Calendar.MONTH, -1)
            updateCalendar()
        })
    }

    /**
     * Display dates correctly in grid
     */
    @JvmOverloads
    fun updateCalendar(events: HashSet<Date>? = null) {
        val cells: ArrayList<Date> = ArrayList()
        val calendar: Calendar = currentDate.clone() as Calendar

        // determine the cell for current month's beginning
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val monthBeginningCell: Int = calendar.get(Calendar.DAY_OF_WEEK) - 1

        // move calendar backwards to the beginning of the week
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell)

        // fill cells
        while (cells.size < DAYS_COUNT) {
            cells.add(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        // update grid
        gvCalendar!!.adapter = CalendarAdapter(context, cells, events)

        // update title
        val sdf = SimpleDateFormat(dateFormat)
        tvTitleCalendar?.text = sdf.format(currentDate.time)
    }

    private inner class CalendarAdapter(context: Context?, days: ArrayList<Date>?, eventDays: HashSet<Date>?) :
        ArrayAdapter<Date?>(context!!, R.layout.layout_item_custom_calendar, days!! as List<Date?>) {
        // days with events
        private val eventDays: HashSet<Date>? = eventDays

        // for view inflation
        private val inflater: LayoutInflater = LayoutInflater.from(context)

        override fun getView(position: Int, view: View?, parent: ViewGroup): View {
            // day in question
            var view: View? = view
            val date: Date? = getItem(position)
            val day: Int? = date?.date
            val month: Int? = date?.month
            val year: Int? = date?.year

            // today
            val today = Date()

            // inflate item if it does not exist yet
            if (view == null) view = inflater.inflate(R.layout.layout_item_custom_calendar, parent, false)

            for (date in listSelected) {
                if (date.date === day && date.month === month && date.year === year) {
                    // mark this day for event
                    view?.cbDaySelected?.isChecked = true
                    break
                }
            }

            view?.cbDaySelected?.setOnClickListener {
                if (view?.cbDaySelected?.isChecked == true) {
                    date?.let { it1 -> listSelected.add(it1) }
                } else {
                    date?.let { it1 -> listSelected.remove(it1) }
                }
                listUpdate.value = listSelected
            }

            val currentMonth: Int = currentDate.get(Calendar.MONTH)
            // clear styling
            view?.tvDay?.setTextColor(resources.getColor(R.color.black))
            view?.cbDaySelected?.isEnabled = true
            if (month != currentMonth) {
                // if this day is outside current month, grey it out
                view?.tvDay?.setTextColor(resources.getColor(R.color.light_gray))
                view?.cbDaySelected?.isEnabled = false
            } else if (day == today.date) {
                // if it is today
            }

            // set text
            view?.tvDay?.text = (java.lang.String.valueOf(date?.date))
            return view!!
        }
    }

    companion object {
        // how many days to show, defaults to five weeks, 35 days
        private const val DAYS_COUNT = 35

        // default date format
        private const val DATE_FORMAT = "MMMM"
    }
}