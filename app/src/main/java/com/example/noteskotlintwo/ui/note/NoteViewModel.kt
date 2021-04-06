package com.example.noteskotlintwo.ui.note

import androidx.lifecycle.ViewModel
import com.example.noteskotlintwo.data.NotesRepository
import com.example.noteskotlintwo.data.entity.Note

class NoteViewModel(): ViewModel() {

    private var pendingNote: Note? = null

    fun save(note:Note){
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let {
            NotesRepository.saveNote(it)
        }
    }

}