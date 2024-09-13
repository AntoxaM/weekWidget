package com.ame.weeknumber

import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale

fun getCurrentWeek(localDate: LocalDate = LocalDate.now()): Int {
    val date = localDate
    val weekFields = WeekFields.of(Locale.FRANCE)
    return date[weekFields.weekOfWeekBasedYear()]
}
