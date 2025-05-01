package com.example.eventcountdown

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(
    private var eventList: List<EventModel>, // Make this mutable
    private val isImportant: Boolean,
    private val onEventClick: (EventModel) -> Unit
) : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event_horizontal, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]

        if (isImportant) {
            val screenWidth = Resources.getSystem().displayMetrics.widthPixels
            holder.itemView.layoutParams.width = (screenWidth * 0.5).toInt()
        } else {
            holder.itemView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        }

        holder.title.text = event.eventTitle
        holder.date.text = event.eventDate
        holder.time.text = event.eventTime

        holder.itemView.setOnClickListener {
            onEventClick(event)
        }
    }

    override fun getItemCount(): Int = eventList.size

    // The updateEvents() method
    @SuppressLint("NotifyDataSetChanged")
    fun updateEvents(newEvents: List<EventModel>) {
        this.eventList = newEvents
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
}
