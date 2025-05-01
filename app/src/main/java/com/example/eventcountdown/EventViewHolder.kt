package com.example.eventcountdown

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.eventTitle)
    val date: TextView = itemView.findViewById(R.id.eventDate)
    val time: TextView = itemView.findViewById(R.id.eventTime)
}