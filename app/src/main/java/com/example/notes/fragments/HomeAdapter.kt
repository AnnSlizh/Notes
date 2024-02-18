package com.example.notes.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.database.Note
import java.text.SimpleDateFormat

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var notesLis = emptyList<Note>()
    private var delete: ((Note) -> Unit)? = null
    private var update: ((Note) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_row, parent, false)
        return HomeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        val currentItem = notesLis[position]

        val date = SimpleDateFormat.getDateTimeInstance().format(currentItem.date)


        if (currentItem.title.isEmpty()) {
            holder.title.text = "No title"
        } else  holder.title.text = currentItem.title

        if (currentItem.description.isEmpty()) {
            holder.description.text = "No description"
        } else  holder.description.text = currentItem.description

        holder.date.text = date

        holder.deleteButton.setOnClickListener {
            delete?.invoke(currentItem)
        }
        holder.itemView.setOnClickListener {
            update?.invoke(currentItem)
        }

    }

    override fun getItemCount(): Int {
        return notesLis.size
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.tv_name)
        val date: TextView = itemView.findViewById(R.id.tv_date)
        val description: TextView = itemView.findViewById(R.id.tv_description)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)

    }

    fun setData(note: List<Note>) {
        this.notesLis = note
        notifyDataSetChanged()
    }

    fun setOnDeleteListener(callback: (Note) -> Unit) {
        this.delete = callback
    }

    fun setOnUpdateListener(callback: (Note) -> Unit) {
        this.update = callback
    }
}