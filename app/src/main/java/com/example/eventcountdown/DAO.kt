package com.example.eventcountdown

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventDao {
    @Insert
    suspend fun insertEvent(event: EventModel): Long

    @Query("SELECT * FROM events")
    suspend fun getAllEvents(): List<EventModel>

    @Query("SELECT * FROM events WHERE isImportant = 1")
    suspend fun getImportantEvents(): List<EventModel>

    @Delete
    suspend fun deleteEvent(event: EventModel)
}
