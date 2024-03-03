package com.example.notes.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.database.HistoryNote
import com.example.notes.database.Note
import java.text.SimpleDateFormat

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var notesLis = emptyList<HistoryNote>()
    private var delete: ((HistoryNote) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.HistoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.story_row, parent, false)
        return HistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
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

    }

    override fun getItemCount(): Int {
        return notesLis.size
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.historyTitle)
        val date: TextView = itemView.findViewById(R.id.historyDate)
        val description: TextView = itemView.findViewById(R.id.historyDescription)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)

    }

    fun setData(note: List<HistoryNote>) {
        this.notesLis = note
        notifyDataSetChanged()
    }

    fun setOnDeleteListener(callback: (HistoryNote) -> Unit) {
        this.delete = callback
    }

}