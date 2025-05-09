package com.example.eventcountdown.logicPackage

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class EventCounterFunction(
    private val textView: TextView,
    eventDateTime: String, // e.g., "2025-04-24 18:00"
    private val dateFormat: String = "yyyy-MM-dd HH:mm"
) {

    private var countDownTimer: CountDownTimer? = null

    init {
        startCountdown(eventDateTime)
    }

    private fun startCountdown(dateTime: String) {
        val sdf = SimpleDateFormat(dateFormat, Locale.getDefault())
        val eventDate: Date?
        try {
            eventDate = sdf.parse(dateTime)
        } catch (e: Exception) {
            textView.text = "Invalid event time"
            return
        }

        val currentTime = System.currentTimeMillis()

        eventDate?.let {
            val diff = it.time - currentTime

            if (diff > 0) {
                countDownTimer = object : CountDownTimer(diff, 60000) {
                    @SuppressLint("DefaultLocale")
                    override fun onTick(millisUntilFinished: Long) {
                        val days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
                        val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 24
                        val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60

                        textView.text = String.format(
                            "Starts in %02d days %02d hrs %02d min",
                            days, hours, minutes
                        )
                    }

                    override fun onFinish() {
                        textView.text = "Event has started!"
                    }
                }.start()
            } else {
                textView.text = "Event already started"
            }
        } ?: run {
            textView.text = "Invalid event time"
        }
    }


    fun cancel() {
        countDownTimer?.cancel()
    }
}
