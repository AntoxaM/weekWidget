package com.ame.weeknumber

import java.time.LocalDate
import java.time.Month

class MonthViewModel(localDate: LocalDate) {
    val week = getCurrentWeek(localDate)
    val month: Month = localDate.month
    val day = localDate.dayOfMonth
    val dayOfWeek = localDate.dayOfWeek
    val year = localDate.year
    val rows = calcRows()

    private fun calcRows(): List<Week> {
        val first = LocalDate.of(year, month, 1)

        var weekStart = first.minusDays(first.dayOfWeek.value - 1L)

        val weeks = mutableListOf<Week>()
        while (weekStart.month <= month) {
            weeks.add(Week(weekStart, month, week))
            weekStart = weekStart.plusDays(7)
        }
        return weeks
    }

    class Week(
        val week: Int,
        val isCurrent: Boolean,
        val days: List<Day>
    ){
        constructor(start: LocalDate, month: Month, week: Int)
                : this(
            week = getCurrentWeek(start),
            isCurrent = week == getCurrentWeek(start),
            (0..6).map {  Day(start.plusDays(it.toLong()), month) },
        )
    }
    class Day(
        val day: Int,
        val isCurrent:Boolean
    ) {
        constructor(date: LocalDate, month: Month)
                : this(day = date.dayOfMonth, isCurrent = month == date.month)
    }
}