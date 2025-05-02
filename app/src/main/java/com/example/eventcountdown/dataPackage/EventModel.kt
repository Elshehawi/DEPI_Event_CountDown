package com.example.eventcountdown.dataPackage

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "events")
data class EventModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val eventTitle: String,
    val eventDate: String,
    val eventTime: String,
    val eventDescription: String,
    val isImportant: Boolean
) : Serializable
