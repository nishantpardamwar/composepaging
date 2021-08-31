package com.nishantpardamwar.composepagingwithroom.db.daos

import androidx.paging.PagingSource
import androidx.room.*
import com.nishantpardamwar.composepagingwithroom.db.entities.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity")
    fun getAllNotesPaginated(): PagingSource<Int, NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteEntity: NoteEntity)

    @Delete
    suspend fun delete(noteEntity: NoteEntity)
}