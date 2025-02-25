package com.example.eventcountdown

import EventAdapter
import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        val recyclerImportantEvents: RecyclerView = findViewById(R.id.recyclerImportantEvents)
        val recyclerUpcomingEvents: RecyclerView = findViewById(R.id.recyclerUpcomingEvents)

        // Important Events RecyclerView (Horizontal) with 2 items visible at a time
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerImportantEvents.layoutManager = layoutManager

        recyclerImportantEvents.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val position = parent.getChildAdapterPosition(view)
                val spacing = 1 // Adjust spacing between items
                outRect.right = spacing
                outRect.left = if (position == 0) spacing else 0
            }
        })
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerImportantEvents)

        recyclerImportantEvents.adapter = EventAdapter(getImportantEvents(), isImportant = true)

        // Upcoming Events RecyclerView (Vertical)
        recyclerUpcomingEvents.layoutManager = LinearLayoutManager(this)
        recyclerUpcomingEvents.adapter = EventAdapter(getUpcomingEvents(), isImportant = true)
    }

    // Important Events List
    private fun getImportantEvents(): List<EventModel> {
        return listOf(
            EventModel("VIP Event A", "16 Feb 2025", "07:30 PM"),
            EventModel("VIP Event B", "20 Mar 2025", "06:00 PM"),
            EventModel("VIP Event C", "25 Apr 2025", "05:45 PM"),
            EventModel("VIP Event A", "16 Feb 2025", "07:30 PM"),
            EventModel("VIP Event B", "20 Mar 2025", "06:00 PM"),
            EventModel("VIP Event C", "25 Apr 2025", "05:45 PM"),
            EventModel("VIP Event A", "16 Feb 2025", "07:30 PM"),
            EventModel("VIP Event B", "20 Mar 2025", "06:00 PM"),
            EventModel("VIP Event C", "25 Apr 2025", "05:45 PM"),
            EventModel("VIP Event D", "10 May 2025", "06:45 PM")
        )
    }

    // Upcoming Events List
    private fun getUpcomingEvents(): List<EventModel> {
        return listOf(
            EventModel("Event X", "10 Mar 2025", "08:00 PM"),
            EventModel("Event Y", "12 Apr 2025", "09:15 AM"),
            EventModel("Event X", "10 Mar 2025", "08:00 PM"),
            EventModel("Event Y", "12 Apr 2025", "09:15 AM"),
            EventModel("Event X", "10 Mar 2025", "08:00 PM"),
            EventModel("Event Y", "12 Apr 2025", "09:15 AM"),
            EventModel("Event X", "10 Mar 2025", "08:00 PM"),
            EventModel("Event Y", "12 Apr 2025", "09:15 AM"),
            EventModel("Event X", "10 Mar 2025", "08:00 PM"),
            EventModel("Event Y", "12 Apr 2025", "09:15 AM"),
            EventModel("Event Z", "05 May 2025", "05:30 PM")
        )
    }
}

