package com.nishantpardamwar.composepagingwithroom.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.nishantpardamwar.composepagingwithroom.db.entities.NoteEntity
import com.nishantpardamwar.composepagingwithroom.repository.datastore.LocalDataStore
import javax.inject.Inject

class RepoImpl @Inject constructor(private val localDS: LocalDataStore) : Repo {
    override fun getNotePagingSource(): PagingSource<Int, NoteEntity> {
        return localDS.getNotePagingSource()
    }

    override suspend fun addNote(noteEntity: NoteEntity) {
        localDS.addNote(noteEntity)
    }

    override suspend fun deleteNote(noteEntity: NoteEntity) {
        localDS.deleteNote(noteEntity)
    }
}