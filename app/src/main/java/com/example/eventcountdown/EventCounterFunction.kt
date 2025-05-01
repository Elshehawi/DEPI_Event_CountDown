package com.example.eventcountdown

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
        val eventDate: Date? = sdf.parse(dateTime)
        val currentTime = System.currentTimeMillis()

        eventDate?.let {
            val diff = it.time - currentTime

            if (diff > 0) {
                countDownTimer = object : CountDownTimer(diff, 60000) { // update every 1 min
                    override fun onTick(millisUntilFinished: Long) {
                        val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                        textView.text = "Starts in $minutes minutes"
                    }

                    override fun onFinish() {
                        textView.text = "Event started"
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