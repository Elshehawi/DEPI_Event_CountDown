package com.example.eventcountdown.dataPackage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventDao {
    @Insert
    suspend fun insertEvent(event: EventModel): Long

    @Delete
    suspend fun deleteEvent(event: EventModel)

    @Query("SELECT * FROM events WHERE id = :eventId LIMIT 1")
    suspend fun getEventById(eventId: Long): EventModel?

    @Query("SELECT * FROM events ORDER BY eventDate ASC, eventTime ASC")
    suspend fun getAllEvents(): List<EventModel>

    @Query("SELECT * FROM events WHERE isImportant = 1 ORDER BY eventDate ASC, eventTime ASC")
    suspend fun getImportantEvents(): List<EventModel>
}
