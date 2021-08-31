package com.nishantpardamwar.composepagingwithroom.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.nishantpardamwar.composepagingwithroom.db.entities.NoteEntity
import com.nishantpardamwar.composepagingwithroom.repository.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    fun getNotePager(): Pager<Int, NoteEntity> {
        return Pager(config = PagingConfig(10)) {
            repo.getNotePagingSource()
        }
    }

    fun addNote(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addNote(noteEntity)
        }
    }

    fun deleteNote(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteNote(noteEntity)
        }
    }
}