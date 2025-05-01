package com.example.eventcountdown.ScreensFragments

import com.example.eventcountdown.DataPackage.EventDatabase
import com.example.eventcountdown.DataPackage.EventModel
import com.example.eventcountdown.EventProperties.EventAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventcountdown.R
import kotlinx.coroutines.launch

class ImportantEventsFragment : Fragment() {

    private lateinit var recyclerImportantEvents: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_important_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerImportantEvents = view.findViewById(R.id.recyclerImportantEvents)

        recyclerImportantEvents.layoutManager = LinearLayoutManager(requireContext())

        loadEventsFromDatabase()
    }

    private fun loadEventsFromDatabase() {
        lifecycleScope.launch {
            val db = EventDatabase.getDatabase(requireContext())
            val importantEvents = db.eventDao().getImportantEvents()

            recyclerImportantEvents.adapter =
                EventAdapter(importantEvents, isImportant = false) { event ->
                    openEventDetails(event)
                }
        }
    }

    private fun openEventDetails(event: EventModel) {
        val fragment = EventDetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable("event", event)
            }
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}
