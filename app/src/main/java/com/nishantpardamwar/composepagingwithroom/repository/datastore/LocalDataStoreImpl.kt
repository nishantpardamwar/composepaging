package com.nishantpardamwar.composepagingwithroom.repository.datastore

import androidx.paging.PagingSource
import com.nishantpardamwar.composepagingwithroom.db.AppDatabase
import com.nishantpardamwar.composepagingwithroom.db.entities.NoteEntity
import javax.inject.Inject

class LocalDataStoreImpl @Inject constructor(private val db: AppDatabase) : LocalDataStore {
    override fun getNotePagingSource(): PagingSource<Int, NoteEntity> {
        return db.noteDao().getAllNotesPaginated()
    }

    override suspend fun addNote(noteEntity: NoteEntity) {
        db.noteDao().insert(noteEntity)
    }

    override suspend fun deleteNote(noteEntity: NoteEntity) {
        db.noteDao().delete(noteEntity)
    }
}