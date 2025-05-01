package com.example.eventcountdown

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class EventDetailsFragment : Fragment() {

    private var eventId: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = arguments?.getString("eventTitle") ?: "No Title"
        val description = arguments?.getString("description") ?: ""
        val datetime = arguments?.getString("datetime") ?: ""
        eventId = arguments?.getLong("event_id")
        val event = arguments?.getSerializable("event") as? EventModel

        val titleView = view.findViewById<TextView>(R.id.text_event_title)
        val descView = view.findViewById<TextView>(R.id.text_event_description)
        val dateView = view.findViewById<TextView>(R.id.text_event_datetime)
        val countdownView = view.findViewById<TextView>(R.id.text_event_countdown)
        val deleteButton = view.findViewById<Button>(R.id.button)

        titleView.text = title
        descView.text = description
        dateView.text = datetime

        // Event counter logic
        if (isValidDateFormat(datetime, "yyyy-MM-dd HH:mm")) {
            EventCounterFunction(countdownView, datetime, "yyyy-MM-dd HH:mm")
        } else {
            countdownView.text = "Invalid date format"
        }

        // Delete event button functionality
        deleteButton.setOnClickListener {
            event?.let {
                lifecycleScope.launch {
                    val db = EventDatabase.getDatabase(requireContext())
                    db.eventDao().deleteEvent(it) // ✅ Delete by object
                    Log.d("EventDetailsFragment", "Deleted event with ID: ${it.id}")

                    // Navigate back
                    parentFragmentManager.popBackStack()
                }
            }
        }
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
