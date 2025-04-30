package com.example.eventcountdown

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var recyclerImportantEvents: RecyclerView
    private lateinit var recyclerUpcomingEvents: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerImportantEvents = view.findViewById(R.id.recyclerImportantEvents)
        recyclerUpcomingEvents = view.findViewById(R.id.recyclerUpcomingEvents)

        recyclerImportantEvents.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        recyclerUpcomingEvents.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        loadEventsFromDatabase()
    }

    private fun loadEventsFromDatabase() {
        lifecycleScope.launch {
            val db = EventDatabase.getDatabase(requireContext())
            val importantEvents = db.eventDao().getImportantEvents()
            val allEvents = db.eventDao().getAllEvents()

            recyclerImportantEvents.adapter = EventAdapter(importantEvents, isImportant = true)
            recyclerUpcomingEvents.adapter = EventAdapter(allEvents, isImportant = false)
        }
    }
}
