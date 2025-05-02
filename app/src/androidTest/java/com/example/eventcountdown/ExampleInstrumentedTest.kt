package com.example.eventcountdown

import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.eventcountdown.logicPackage.EventCounterFunction
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.*

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun countdown_showsCorrectInitialFormat() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            // Given: A future event datetime (1 day from now)
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            val futureDate = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(calendar.time)

            // When: Creating the countdown timer
            val context = ApplicationProvider.getApplicationContext<android.content.Context>()
            val textView = TextView(context)
            val countdown = EventCounterFunction(textView, futureDate)

            // Wait for timer tick
            Handler(Looper.getMainLooper()).postDelayed({
                val text = textView.text.toString()
                assertTrue("Text should start with 'Starts in'", text.startsWith("Starts in") || text == "Event has started!")
                countdown.cancel()
            }, 1000)
        }

        // Optionally wait a bit to ensure the timer updates the text
        Thread.sleep(1500)
    }

    @Test
    fun countdown_showsInvalidFormat() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            val context = ApplicationProvider.getApplicationContext<android.content.Context>()
            val textView = TextView(context)

            // When: Giving it a wrongly formatted date
            val countdown = EventCounterFunction(textView, "invalid-date")

            // Then: Text should say "Invalid event time"
            assertEquals("Invalid event time", textView.text.toString())
        }
    }
}