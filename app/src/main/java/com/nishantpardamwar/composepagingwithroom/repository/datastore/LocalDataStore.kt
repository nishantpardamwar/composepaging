package com.nishantpardamwar.composepagingwithroom.repository.datastore

import androidx.paging.PagingSource
import com.nishantpardamwar.composepagingwithroom.db.entities.NoteEntity

interface LocalDataStore {
    fun getNotePagingSource(): PagingSource<Int, NoteEntity>
    suspend fun addNote(noteEntity: NoteEntity)
    suspend fun deleteNote(noteEntity: NoteEntity)
}