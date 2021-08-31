package com.nishantpardamwar.composepagingwithroom.repository

import androidx.paging.PagingSource
import com.nishantpardamwar.composepagingwithroom.db.entities.NoteEntity

interface Repo {
    fun getNotePagingSource(): PagingSource<Int, NoteEntity>
    suspend fun addNote(noteEntity: NoteEntity)
    suspend fun deleteNote(noteEntity: NoteEntity)
}