package com.example.notes.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.database.Note
import com.example.notes.database.viewModel.HistoryNoteViewModel
import com.example.notes.database.viewModel.NoteViewModel

class HistoryFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistoryAdapter

    private lateinit var currentNote: Note
    private val historyNoteArgs: HistoryFragmentArgs by navArgs<HistoryFragmentArgs>()

    private lateinit var historyNoteViewModel: HistoryNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_story, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyNoteViewModel = ViewModelProvider(this)[HistoryNoteViewModel::class.java]

        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.recycleViewStory)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        adapter = HistoryAdapter()
        recyclerView.adapter = adapter

        getNotes()

        adapter.setOnDeleteListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Do you want to delete a note?")
            builder.setPositiveButton("Yes") { p0, p1 ->
                historyNoteViewModel.deleteNote(it)
                p0.dismiss()

                getNotes()
            }

            builder.setNegativeButton("No") { p0, p1 ->
                p0.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }


    }

    private fun getNotes() {
        currentNote = historyNoteArgs.currentNote

        historyNoteViewModel.getNote(currentNote.id)
            .observe(viewLifecycleOwner, Observer { notes ->
                adapter.setData(notes)
            })
    }

}