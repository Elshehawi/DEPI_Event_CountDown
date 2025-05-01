package com.example.eventcountdown

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var recyclerImportantEvents: RecyclerView
    private lateinit var recyclerUpcomingEvents: RecyclerView
    private lateinit var eventAdapter: EventAdapter
    private var allEvents: List<EventModel> = listOf()

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
        val searchView = view.findViewById<SearchView>(R.id.searchBar)

        recyclerImportantEvents.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        recyclerUpcomingEvents.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        // Setup the upcoming events adapter and recycler view
        eventAdapter = EventAdapter(allEvents, isImportant = false) { event ->
            openEventDetails(event)
        }
        recyclerUpcomingEvents.adapter = eventAdapter

        // Load data from database
        loadEventsFromDatabase()

        // Search functionality
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false // we handle it as they type
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filtered = allEvents.filter  {
                    it.eventTitle.contains(newText ?: "", ignoreCase = true)
                }

                eventAdapter.updateEvents(filtered)
                return true
            }
        })
    }

    private fun loadEventsFromDatabase() {
        lifecycleScope.launch {
            val db = EventDatabase.getDatabase(requireContext())
            val importantEvents = db.eventDao().getImportantEvents()
            allEvents = db.eventDao().getAllEvents()

            // Log events to ensure they're being loaded correctly
            allEvents.forEach {
                Log.d("TEST_ID", "Event title: ${it.eventTitle}, ID: ${it.id}")
            }

            // Update the adapter with important events
            recyclerImportantEvents.adapter =
                EventAdapter(importantEvents, isImportant = true) { event ->
                    openEventDetails(event)
                }

            // Update the adapter for upcoming events
            eventAdapter.updateEvents(allEvents)
        }
    }

    private fun openEventDetails(event: EventModel) {
        val fragment = EventDetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable("event", event) // Pass the entire event object
            }
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

}
