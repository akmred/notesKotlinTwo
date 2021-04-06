package com.example.noteskotlintwo.ui.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.noteskotlintwo.R
import com.example.noteskotlintwo.ui.note.NoteActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var viewModel: MainViewModel
    lateinit var adapter: NotesRVAdapter

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))//Не забудьте поменять в Стилях приложения тему на Theme.AppCompat.Light.NoActionBar

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        adapter = NotesRVAdapter{
            NoteActivity.start(this, it)
        }

        val main = findViewById<RecyclerView>(R.id.rv_notes)
        main.adapter = adapter

        viewModel.viewState().observe(this, Observer<MainViewState> { t ->
            t?.let { adapter.notes = it.notes }
        })

        fab.setOnClickListener{
          NoteActivity.start(this, null)
        }
    }
}