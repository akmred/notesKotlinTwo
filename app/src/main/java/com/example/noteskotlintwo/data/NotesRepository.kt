package com.example.noteskotlintwo.data

import com.example.noteskotlintwo.data.entity.Note

object NotesRepository {

    private val notes: List<Note> = listOf(
            Note("first", "first text", 0xf3f06292.toInt()),
            Note("second", "second text", 0xfff06092.toInt()),
            Note("third", "third text", 0xfff06202.toInt()),
            Note("forth", "forth text", 0xfff06102.toInt()),
            Note("fifth", "fifth text", 0xfff06292.toInt()),
            Note("sexth", "sexth text", 0xfff06222.toInt()),
            Note("seventh", "seventh text", 0xfff11292.toInt()),
            Note("ninth", "ninth text", 0xfff05592.toInt())
            );


    fun getNotes(): List<Note> = notes
}