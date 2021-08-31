package com.nishantpardamwar.composepagingwithroom.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "NoteEntity")
data class NoteEntity(
    @ColumnInfo(name = "noteTitle") val noteTitle: String,
    @ColumnInfo(name = "noteDescription") val noteDescription: String,
    @ColumnInfo(name = "createdAt") val createdAt: Long
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

    fun displayCreatedAt(): String {
        val date = Date(createdAt)
        val format = SimpleDateFormat("d-MMM-yyyy hh:mm a", Locale.getDefault())
        return format.format(date)
    }
}