package com.example.noteskotlintwo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.noteskotlintwo.data.entity.Note
import java.util.*

object NotesRepository {

    private val notesLiveData = MutableLiveData<List<Note>>()

    private val notes: MutableList<Note> = mutableListOf(
            Note(UUID.randomUUID().toString(),"first", "first text", color = Note.Color.WHITE),
            Note(UUID.randomUUID().toString(),"second", "second text", color = Note.Color.GREEN),
            Note(UUID.randomUUID().toString(),"third", "third text", color = Note.Color.BLUE),
            Note(UUID.randomUUID().toString(),"forth", "forth text", color = Note.Color.GREEN),
            Note(UUID.randomUUID().toString(),"fifth", "fifth text", color = Note.Color.BLUE),
            Note(UUID.randomUUID().toString(),"sexth", "sexth text", color = Note.Color.GREEN),
            Note(UUID.randomUUID().toString(),"seventh", "seventh text", color = Note.Color.WHITE),
            Note(UUID.randomUUID().toString(),"ninth", "ninth text", color = Note.Color.RED),
            Note(UUID.randomUUID().toString(),"first", "first text", color = Note.Color.WHITE),
            Note(UUID.randomUUID().toString(),"second", "second text", color = Note.Color.GREEN),
            Note(UUID.randomUUID().toString(),"third", "third text", color = Note.Color.WHITE),
            Note(UUID.randomUUID().toString(),"ninth", "ninth text", color = Note.Color.WHITE)
            );

    init {
        notesLiveData.value = notes
    }

        fun saveNote(note: Note){
                addOrReplace(note)
                notesLiveData.value = notes
        }

        fun addOrReplace(note: Note){
                for(i in 0 until notes.size){
                        if(notes[i] == note){
                                notes[i] = note
                                return
                        }
                }
                notes.add(note)
        }


    fun getNotes(): LiveData<List<Note>> = notesLiveData
}