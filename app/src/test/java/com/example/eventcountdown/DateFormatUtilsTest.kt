package com.example.eventcountdown

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DateFormatUtilsTest {

    @Test
    fun isValidDateFormat_validFormat_returnsTrue() {
        val date = "2025-04-24 18:00"
        val format = "yyyy-MM-dd HH:mm"
        val result = isValidDateFormat(date, format)
        assertTrue(result)
    }

    @Test
    fun isValidDateFormat_invalidFormat_returnsFalse() {
        val date = "invalid-date"
        val format = "yyyy-MM-dd HH:mm"
        val result = isValidDateFormat(date, format)
        assertFalse(result)
    }

    // Helper function (or import it if you already have it)
    fun isValidDateFormat(date: String, format: String): Boolean {
        return try {
            val sdf = java.text.SimpleDateFormat(format, java.util.Locale.getDefault())
            sdf.isLenient = false
            sdf.parse(date) != null
        } catch (e: Exception) {
            false
        }
    }
}
