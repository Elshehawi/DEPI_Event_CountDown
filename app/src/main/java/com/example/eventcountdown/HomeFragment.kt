package com.example.eventcountdown

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import EventAdapter
import androidx.appcompat.app.AppCompatActivity

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerImportantEvents: RecyclerView = view.findViewById(R.id.recyclerImportantEvents)
        val recyclerUpcomingEvents: RecyclerView = view.findViewById(R.id.recyclerUpcomingEvents)

        // Set up Important Events RecyclerView (Horizontal)
        recyclerImportantEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerImportantEvents.adapter = EventAdapter(getImportantEvents(), isImportant = true)

        // Set up Upcoming Events RecyclerView (Vertical)
        recyclerUpcomingEvents.layoutManager = LinearLayoutManager(requireContext())
        recyclerUpcomingEvents.adapter = EventAdapter(getUpcomingEvents(),  isImportant = false)
    }

    private fun getImportantEvents(): List<EventModel> {
        return listOf(
            EventModel("VIP Event A", "16 Feb 2025", "07:30 PM"),
            EventModel("VIP Event B", "20 Mar 2025", "06:00 PM"),
            EventModel("VIP Event C", "16 Feb 2025", "07:30 PM"),
            EventModel("VIP Event D", "20 Mar 2025", "06:00 PM"),
            EventModel("VIP Event E", "16 Feb 2025", "07:30 PM"),
            EventModel("VIP Event F", "20 Mar 2025", "06:00 PM"),
            EventModel("VIP Event G", "25 Apr 2025", "05:45 PM")
        )
    }

    private fun getUpcomingEvents(): List<EventModel> {
        return listOf(
            EventModel("Event X", "10 Mar 2025", "08:00 PM"),
            EventModel("Event Y", "12 Apr 2025", "09:15 AM"),
            EventModel("Event Z", "10 Mar 2025", "08:00 PM"),
            EventModel("Event F", "12 Apr 2025", "09:15 AM"),
            EventModel("Event A", "10 Mar 2025", "08:00 PM"),
            EventModel("Event E", "12 Apr 2025", "09:15 AM"),
            EventModel("Event G", "05 May 2025", "05:30 PM")
        )
    }
}
