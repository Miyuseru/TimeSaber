package app.miyuseru.timesaber

import java.text.SimpleDateFormat
import java.util.*

class DateManager {

    var mCalendar: Calendar = Calendar.getInstance()

    fun getDays(): List<Date>? {
        val startDate: Date = mCalendar.time

        val count = getWeeks() * 7
        mCalendar.set(Calendar.DATE, 1)
        val dayOfWeek: Int = mCalendar.get(Calendar.DAY_OF_WEEK) - 1
        mCalendar.add(Calendar.DATE, -dayOfWeek)
        val days: MutableList<Date> = ArrayList()

        for (i in 0 until count) {
            days.add(mCalendar.time)
            mCalendar.add(Calendar.DATE, 1)
        }
        mCalendar.time = startDate
        return days

    }

    fun isCurrentMonth(date: Date?): Boolean {
        val format = SimpleDateFormat("yyyy.MM", Locale.JAPAN)
        val currentMonth: String = format.format(mCalendar.time)
        return currentMonth == format.format(date)
    }

    fun getWeeks(): Int {
        return mCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH)
    }

    fun getDayOfWeek(date: Date?): Int {
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.DAY_OF_WEEK)

    }

    fun nextMonth() {
        mCalendar.add(Calendar.MONTH, 1)

    }

    fun prevMonth() {
        mCalendar.add(Calendar.MONTH, -1)
    }




}