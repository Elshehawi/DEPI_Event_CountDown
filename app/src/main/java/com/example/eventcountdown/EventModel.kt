package com.example.eventcountdown

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventModel(
    val eventTitle: String,
    val eventDate: String,
    val eventTime: String,
    val eventDescription: String,
    val isImportant: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)