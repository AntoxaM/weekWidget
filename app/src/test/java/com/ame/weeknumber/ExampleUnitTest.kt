package com.ame.weeknumber

import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(27, getCurrentWeek(LocalDate.of(2024, 7, 7)))
        assertEquals(28, getCurrentWeek(LocalDate.of(2024, 7, 8)))
    }
}