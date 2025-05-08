package com.example.eventcountdown

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.eventcountdown.dataPackage.EventDatabase
import com.example.eventcountdown.dataPackage.EventModel
import com.example.eventcountdown.logicPackage.EventCounterFunction
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class EventDetailsFragment : Fragment() {

    private var event: EventModel? = null
    private var countdownTimer: EventCounterFunction? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get event passed via Bundle
        event = arguments?.getSerializable("event") as? EventModel

        val titleView = view.findViewById<TextView>(R.id.text_event_title)
        val descView = view.findViewById<TextView>(R.id.text_event_description)
        val dateView = view.findViewById<TextView>(R.id.text_event_datetime)
        val countdownView = view.findViewById<TextView>(R.id.text_event_countdown)
        val deleteButton = view.findViewById<Button>(R.id.button)

        event?.let { ev ->
            titleView.text = ev.eventTitle
            descView.text = ev.eventDescription
            dateView.text = "Day: " + ev.eventDate + "\nTime: " + ev.eventTime

            val fullDateTime = "${ev.eventDate} ${ev.eventTime}"

            // Validate and start countdown
            if (isValidDateFormat(fullDateTime, "dd-MM-yyyy HH:mm")) {
                countdownTimer = EventCounterFunction(countdownView, fullDateTime, "dd-MM-yyyy HH:mm")
            } else {
                countdownView.text = "Invalid date format"
            }

            // Delete event
            deleteButton.setOnClickListener {
                lifecycleScope.launch {
                    val db = EventDatabase.getDatabase(requireContext())
                    db.eventDao().deleteEvent(ev)
                    Toast.makeText(requireContext(), "Event Deleted!", Toast.LENGTH_SHORT).show()
                    Log.d("EventDetailsFragment", "Deleted event with ID: ${ev.id}")
                    parentFragmentManager.popBackStack()
                }
            }
        } ?: run {
            Log.e("EventDetailsFragment", "Event object is null!")
        }
    }

    private fun isValidDateFormat(date: String, format: String): Boolean {
        return try {
            val sdf = SimpleDateFormat(format, Locale.getDefault())
            sdf.isLenient = false
            sdf.parse(date) != null
        } catch (e: Exception) {
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countdownTimer?.cancel()
    }
}
