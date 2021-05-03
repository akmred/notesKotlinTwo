package com.example.noteskotlintwo.ui.note

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.noteskotlintwo.R
import com.example.noteskotlintwo.data.entity.Note
import com.example.noteskotlintwo.data.entity.Note.Color.*
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {

    companion object{
        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE"
        private const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, note: Note? = null) =
            Intent(context, NoteActivity::class.java)?.run {

                putExtra(EXTRA_NOTE, note)
                context.startActivity(this)
            }
    }

    private var note:Note? = null

    lateinit var viewModel: NoteViewModel

    lateinit var titleEt: TextInputEditText
    lateinit var bodyEt: EditText

    val textChangeListener = object : TextWatcher{
        override fun afterTextChanged(s: Editable?) {

            saveNote()

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acivity_note)

        setSupportActionBar(findViewById(R.id.toolbar))//Не забудьте поменять в Стилях приложения тему на Theme.AppCompat.Light.NoActionBar

        supportActionBar?.setDisplayShowHomeEnabled(true)
        note = intent.getParcelableExtra(EXTRA_NOTE)

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        titleEt = findViewById(R.id.titleEt)
        bodyEt = findViewById(R.id.bodyEt)

        initView()

        supportActionBar?.title = note?.let {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(it.lastChanged)
        } ?:let {
            getString(R.string.note_new)
        }

    }

    fun initView(){
        note?.let {
            titleEt.setText(it.title ?: "")
            bodyEt.setText(it.text)
            val color:Int = when(it.color){
                Note.Color.WHITE -> R.color.color_white
                Note.Color.YELLOW -> R.color.color_yello
                Note.Color.GREEN -> R.color.color_green
                Note.Color.RED -> R.color.color_red
                Note.Color.BLUE -> R.color.color_green
                Note.Color.VIOLET -> R.color.color_violet
            }

            toolbar.setBackgroundColor(ResourcesCompat.getColor(resources, color, null))
        }

        titleEt.addTextChangedListener(textChangeListener)
        bodyEt.addTextChangedListener(textChangeListener)

    }

    fun saveNote() {
        if (titleEt.text.isNullOrBlank()) return

        // если заметка существует, то обновляем текущую
        note = note?.copy(
            title = titleEt.text.toString(),
            text = bodyEt.text.toString(),
            lastChanged = Date()
                // если с нуля заметку вводим, то создаем новую заметку
        )?: Note(UUID.randomUUID().toString(), title = titleEt.text.toString(),
        text = bodyEt.text.toString())

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {

        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }
}