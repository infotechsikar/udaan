package com.dr.udaan.ui.fragments.notes

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dr.udaan.MyApp
import com.dr.udaan.R
import com.dr.udaan.room.Note
import com.dr.udaan.util.loadByteArray

class NotesAdapter(val onNoteClick: (note: Note) -> Unit) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    private var notes: List<Note> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_item_notes, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val note = notes[position]
        holder.title.text = note.title
        holder.content.text = note.content
        holder.image.loadByteArray(note.imageBytes)

        Log.d("TAG", "onBindViewHolder: ${note.id}")
        holder.itemView.setOnClickListener {
            onNoteClick(note)
        }

        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this note?")
                .setPositiveButton("Yes"){_,_->
                    MyApp.myDatabase?.noteDao()?.deleteById(note.id)
                    notifyItemRemoved(position)
                }.setNegativeButton("No", null).create().show()
            false
        }

    }

    override fun getItemCount() = notes.size

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.note_title)
        val content: TextView = itemView.findViewById(R.id.note_content)
        val image: ImageView = itemView.findViewById(R.id.note_image)
    }
}
