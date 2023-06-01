package com.example.mynoteapps.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mynoteapps.database.Note
import com.example.mynoteapps.repository.NoteRepository

class MainViewModel(app : Application) : ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(app)

    fun getAllNotes(): LiveData<List<Note>> = mNoteRepository.getAllNotes()
}