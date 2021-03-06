package com.gmail.pmanenok.personalorganizer.presentation.views

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.support.annotation.RequiresApi
import android.text.format.DateFormat
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import com.gmail.pmanenok.personalorganizer.R
import kotlinx.android.synthetic.main.include_calendar_layout.view.*
import kotlinx.android.synthetic.main.include_calendar_month_title_layout.view.*
import java.lang.ref.WeakReference
import java.util.*


class CalendarView : GridLayout {
    private lateinit var view: View
    private var currentCalendar: Calendar = Calendar.getInstance()
    private lateinit var previousMonthButton: ImageButton
    private lateinit var nextMonthButton: ImageButton
    private lateinit var weeks: List<View>
    private lateinit var monthTitleView: TextView
    private val cells = mutableListOf<CellView>()
    private val dayTags =
        listOf("week_day1", "week_day2", "week_day3", "week_day4", "week_day5", "week_day6", "week_day7")
    private var cellOnClickListener: View.OnClickListener? = null
    private lateinit var currentDay: Day
    private var downY: Float = 0f
    private var moveY: Float = 0f
    private var upY: Float = 0f
    private var momentOfTouch: Long = 0L
    private var listenTouch: Boolean = false
    private var longClick: Boolean = false
    private var longClickPerformed: Boolean = false
    var adapter: CalendarView.NoteAdapter? = null
        set(value) {
            Log.e("CalendarView", "adapter set calendar reset ")
            value?.calendarView = WeakReference(this)
            field = value
            if (value != null) {
                setCalendar(value.getCalendar(), value.autoUpdate)
            }
        }
    private var selectedDay: Long = 0L
        set(value) {
            if (field != value || currentCalendar.timeInMillis != value) {
                field = value
                currentCalendar.timeInMillis = field
                updateCalendar()
            }
        }

    constructor(context: Context) : super(context) {
        initializeCalendar()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        //getAttributes(attrs)

        initializeCalendar()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        //getAttributes(attrs)

        initializeCalendar()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes) {
        //getAttributes(attrs)

        initializeCalendar()
    }

    fun setCellOnClickListener(cellOnClickListener: View.OnClickListener?) {
        if (this.cellOnClickListener != cellOnClickListener) {
            this.cellOnClickListener = cellOnClickListener
            for (cell in cells) {
                cell.setOnClickListener(this.cellOnClickListener)
            }
        }
    }

    //private val customTypeface: Typeface? = null
    //private val locale: Locale? = null
    //private lateinit var selectedDate: Date
    //private var calendarListener: CalendarListener? = null
    //private lateinit var today: Date

    private fun initializeCalendar() {
        Log.e("CalendarView", "initializeCalendar")
        val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflate.inflate(R.layout.include_calendar_layout, this, true)
        weeks = listOf(week_1_include, week_2_include, week_3_include, week_4_include, week_5_include, week_6_include)
        previousMonthButton = month_title_back
        previousMonthButton.setOnClickListener {
            val prevMonth = currentCalendar[Calendar.MONTH] - 1
            if (prevMonth == 12) {
                currentCalendar.set(Calendar.MONTH, 11)
                currentCalendar.roll(Calendar.YEAR, false)
            } else {
                currentCalendar.set(Calendar.MONTH, prevMonth)
            }
            updateCalendar()
        }
        nextMonthButton = month_title_forward
        nextMonthButton.setOnClickListener {
            val nextMonth = currentCalendar[Calendar.MONTH] + 1
            if (nextMonth == 12) {
                currentCalendar.set(Calendar.MONTH, 0)
                currentCalendar.roll(Calendar.YEAR, true)
            } else {
                currentCalendar.set(Calendar.MONTH, nextMonth)
            }
            updateCalendar()
        }
        monthTitleView = month_title_tab_include.findViewById<TextView>(R.id.month_title)
        for (week in weeks) {
            for (tag in dayTags) {
                val cell = week.findViewWithTag<CellView>(tag)
                cells.add(cell)
            }
        }
        currentDay = Day(getDayTimeInMilliseconds(Calendar.getInstance().timeInMillis))
        selectedDay = currentDay.longTime
    }

    private fun setCalendar(calendar: Calendar, autoUpdate: Boolean = false) {
        Log.e("CalendarView", "setCalendar")
        calendar.firstDayOfWeek = Calendar.MONDAY
        calendar.timeInMillis = getDayTimeInMilliseconds(calendar.timeInMillis)
        this.currentCalendar = calendar
        invalidate()
        updateCalendar()
        if (autoUpdate) {
            val newCalendar = adapter?.getCalendar() ?: Calendar.getInstance()
            Handler().postDelayed({ setCalendar(newCalendar, true) }, 60000)
        }
    }

    private fun updateCalendar() {

        Log.e("CalendarView", "${currentDay.day},${currentDay.month},${currentDay.year},${currentDay.longTime}")
        val currentMonth = currentCalendar.get(Calendar.MONTH)
        val currentTime = currentCalendar.timeInMillis
        monthTitleView.text = DateFormat.format("MMMM, yyyy", currentTime).toString().toUpperCase()
        getFirstShowingDay(currentCalendar)
        for (cell in cells) {
            val dayId = getDayTimeInMilliseconds(currentCalendar.timeInMillis)
            cell.dayId = dayId
            cell.text = currentCalendar.get(Calendar.DAY_OF_MONTH).toString()
            if (dayId == currentDay.longTime && dayId == selectedDay) cell.setBorderColor(Color.MAGENTA)
            else when (dayId) {
                currentDay.longTime -> cell.setBorderColor(Color.RED)
                selectedDay -> cell.setBorderColor(Color.BLUE)
                else -> cell.resetBorderColor()
            }
            if (currentCalendar.get(Calendar.MONTH) == currentMonth)
                cell.setTextColor(Color.BLACK)
            else cell.resetTextColor()
            cell.clearNotes()
            cell.setNotes(adapter?.getNotesForDay(dayId))
            currentCalendar.set(Calendar.DATE, currentCalendar[Calendar.DATE] + 1)
        }
        currentCalendar.timeInMillis = currentTime
    }

    private fun getFirstShowingDay(calendar: Calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfWeekInMonth = calendar[Calendar.DAY_OF_WEEK]
        if (firstDayOfWeekInMonth == calendar.firstDayOfWeek) {
            calendar.set(Calendar.DAY_OF_MONTH, -6)
        } else {
            while (calendar[Calendar.DAY_OF_WEEK] != calendar.firstDayOfWeek)
                calendar.roll(Calendar.DAY_OF_WEEK, false)
        }
    }

    private fun getDayTimeInMilliseconds(time: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //val x = event?.x ?: 0f
        val y = event?.y ?: 0f
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
                if ((y > downY && y < moveY) || (y < downY && y > moveY)) {
                    listenTouch = false
                } else if (listenTouch && Math.abs(moveY - y) > 20f) {
                    moveY = y
                }
                return true
            }
            MotionEvent.ACTION_UP -> {
                upY = y
                if (downY < upY && upY - downY > 200f && listenTouch) {
                    previousMonthButton.performClick()
                } else if (downY > upY && downY - upY > 200f && listenTouch) {
                    nextMonthButton.performClick()
                }
                listenTouch = false
                return false
            }
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        //val x = event?.x ?: 0f
        val y = event?.y ?: 0f
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                longClick = true
                downY = y
                moveY = y
                Handler().postDelayed({
                    if (longClick) {
                        longClickPerformed = true
                        performLongClick()
                    }
                }, 1000)
            }
            MotionEvent.ACTION_MOVE -> {
                if (Math.abs(downY - y) > 20f) {
                    listenTouch = true
                    longClick = false
                    return true
                }
            }
            MotionEvent.ACTION_UP -> {
                if (!longClickPerformed && longClick) {
                    longClick = false
                }
                else if(longClickPerformed){
                    longClick = false
                    longClickPerformed = false
                    return true
                }
            }
            MotionEvent.ACTION_CANCEL -> {
                longClick = false
            }
        }
        return super.onInterceptTouchEvent(event)
    }

    /*private fun getAttributes(attrs: AttributeSet) {
    *//* var typedArray: TypedArray =
             mContext.obtainStyledAttributes(attrs, R.styleable.CustomCalendarView, 0, 0);
         calendarBackgroundColor = typedArray.getColor(
             R.styleable.CustomCalendarView_calendarBackgroundColor,
             getResources().getColor(R.color.white)
         );
         calendarTitleBackgroundColor = typedArray.getColor(
             R.styleable.CustomCalendarView_titleLayoutBackgroundColor,
             getResources().getColor(R.color.white)
         );
         calendarTitleTextColor = typedArray.getColor(
             R.styleable.CustomCalendarView_calendarTitleTextColor,
             getResources().getColor(R.color.black)
         );
         weekLayoutBackgroundColor = typedArray.getColor(
             R.styleable.CustomCalendarView_weekLayoutBackgroundColor,
             getResources().getColor(R.color.white)
         );
         dayOfWeekTextColor = typedArray.getColor(
             R.styleable.CustomCalendarView_dayOfWeekTextColor,
             getResources().getColor(R.color.black)
         );
         dayOfMonthTextColor = typedArray.getColor(
             R.styleable.CustomCalendarView_dayOfMonthTextColor,
             getResources().getColor(R.color.black)
         );
         disabledDayBackgroundColor = typedArray.getColor(
             R.styleable.CustomCalendarView_disabledDayBackgroundColor,
             getResources().getColor(R.color.day_disabled_background_color)
         );
         disabledDayTextColor = typedArray.getColor(
             R.styleable.CustomCalendarView_disabledDayTextColor,
             getResources().getColor(R.color.day_disabled_text_color)
         );
         selectedDayBackground = typedArray.getColor(
             R.styleable.CustomCalendarView_selectedDayBackgroundColor,
             getResources().getColor(R.color.selected_day_background)
         );
         selectedDayTextColor = typedArray.getColor(
             R.styleable.CustomCalendarView_selectedDayTextColor,
             getResources().getColor(R.color.white)
         );
         currentDayOfMonth = typedArray.getColor(
             R.styleable.CustomCalendarView_currentDayOfMonthColor,
             getResources().getColor(R.color.current_day_of_month)
         );
         typedArray.recycle();*//*
    }*/

    private inner class Day(timeInMillis: Long) {
        val longTime: Long
        val day: Int
        val month: Int
        val year: Int
        val dayOfWeek: Int

        init {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timeInMillis
            this.longTime = calendar.timeInMillis
            this.day = calendar[Calendar.DAY_OF_MONTH]
            this.month = calendar[Calendar.MONTH]
            this.year = calendar[Calendar.YEAR]
            this.dayOfWeek = calendar[Calendar.DAY_OF_WEEK]
        }
    }

    abstract class NoteAdapter(private val itemList: MutableMap<Long, List<Int>> = mutableMapOf()) {
        var calendarView: WeakReference<CalendarView>? = null
        var autoUpdate: Boolean = false
        private var zone: TimeZone? = null
        private var locale: Locale? = null

        fun setCalendarParams(zone: TimeZone? = null, locale: Locale? = null, autoUpdate: Boolean = false) {
            this.zone = zone
            this.locale = locale
            this.autoUpdate = autoUpdate
        }

        fun getCalendar(): Calendar {
            return if (zone != null && locale != null) Calendar.getInstance(zone, locale)
            else if (zone != null) Calendar.getInstance(zone)
            else if (locale != null) Calendar.getInstance(locale)
            else Calendar.getInstance()
        }

        fun setItems(items: MutableMap<Long, List<Int>>) {
            itemList.clear()
            itemList.putAll(items)
            Log.e("NoteAdapter setItems", "dataChanged $itemList")
            calendarView?.get()?.updateCalendar()
        }

        fun addItems(items: MutableMap<Long, List<Int>>) {
            itemList.putAll(items)
            calendarView?.get()?.updateCalendar()
        }

        fun cleanItems() {
            itemList.clear()
            calendarView?.get()?.updateCalendar()
        }

        abstract fun getMoreDays(dayId: Long, longTime: Long)

        fun getNotesForDay(dayId: Long): List<Int>? {
            if (!itemList.containsKey(dayId))
                getMoreDays(dayId, calendarView?.get()?.currentDay!!.longTime)
            return itemList[dayId]
        }

        fun setSelectedDay(timeInMillis: Long) {
            Log.e("NoteAdapter", "setSelectedDay")
            calendarView?.get()?.selectedDay = timeInMillis
        }
    }
}