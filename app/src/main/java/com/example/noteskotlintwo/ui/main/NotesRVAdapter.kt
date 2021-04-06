package com.example.noteskotlintwo.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.noteskotlintwo.R
import com.example.noteskotlintwo.data.entity.Note
import kotlinx.android.synthetic.main.acivity_note.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_note.view.*

class NotesRVAdapter(val onItemClick: ((Note) -> Unit)? = null) : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int): Unit {
        holder.bind(notes[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note) = with(itemView) {
            // todo tv_title, tv_body
            et_title.text = note.title
            et_body.text = note.text

            val color: Int = when (note.color) {
                Note.Color.WHITE -> R.color.color_white
                Note.Color.YELLOW -> R.color.color_yello
                Note.Color.GREEN -> R.color.color_green
                Note.Color.BLUE -> R.color.color_green
                Note.Color.VIOLET -> R.color.color_violet
            }
            // todo itemView добавил
            setBackgroundColor(ResourcesCompat.getColor(resources, color, null))

            setOnClickListener {
                onItemClick?.invoke(note)
            }
        }
    }

}