package com.example.eventcountdown

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(private val eventList: List<EventModel>, private val isImportant: Boolean) :
    RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event_horizontal, parent, false)
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
    }

    override fun getItemCount(): Int = eventList.size
    
}

class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.eventTitle)
    val date: TextView = itemView.findViewById(R.id.eventDate)
    val time: TextView = itemView.findViewById(R.id.eventTime)
}