package com.nishantpardamwar.composepagingwithroom.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nishantpardamwar.composepagingwithroom.db.daos.NoteDao
import com.nishantpardamwar.composepagingwithroom.db.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}