package com.example.noteskotlintwo.ui.note

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.noteskotlintwo.R
import com.example.noteskotlintwo.data.entity.Note
import com.example.noteskotlintwo.data.entity.Note.Color.*
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
        supportActionBar?.title = note?.let {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(it.lastChanged)
        } ?:let {
            getString(R.string.note_new)
        }

         fun initView(){
            note?.let {
                et_title.setText(it.title ?: "")
                et_body.setText(it.text)
                val color:Int = when(it.color){
                    Note.Color.WHITE -> R.color.color_white
                    Note.Color.YELLOW -> R.color.color_yello
                    Note.Color.GREEN -> R.color.color_green
                    Note.Color.BLUE -> R.color.color_green
                    Note.Color.VIOLET -> R.color.color_violet
                }

                toolbar.setBackgroundColor(ResourcesCompat.getColor(resources, color, null))
            }

            et_title.addTextChangedListener(textChangeListener)
            et_body.addTextChangedListener(textChangeListener)

        }
    }

    fun saveNote() {
        if (!et_title.text.isNullOrBlank()) return

        note = note?.copy(
            title = et_title.text.toString(),
            text = et_body.text.toString(),
            lastChanged = Date()
        )?: Note(UUID.randomUUID().toString(), title = et_title.text.toString(),
        text = et_body.text.toString())

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {

        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }
}