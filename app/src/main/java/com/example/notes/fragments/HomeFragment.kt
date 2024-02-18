package com.example.notes.fragments

import android.app.AlertDialog
import android.icu.text.MessagePattern.ArgType
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.database.viewModel.NoteViewModel
import com.example.notes.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : Fragment() {

    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HomeAdapter

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        floatingActionButton = view.findViewById(R.id.floatingActionButton)

        floatingActionButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_addNoteFragment)
        }


        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.recycleViewNotes)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        adapter = HomeAdapter()
        recyclerView.adapter = adapter

        getNotes()

        adapter.setOnDeleteListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Do you want to delete a note?")
            builder.setPositiveButton("Yes") { p0, p1 ->
                noteViewModel.deleteNote(it)
                p0.dismiss()

                getNotes()
            }

            builder.setNegativeButton("No") { p0, p1 ->
                p0.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }


        adapter.setOnUpdateListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(it)
            Navigation.findNavController(view).navigate(action)

        }
    }

    private fun getNotes() {
        noteViewModel.allData.observe(viewLifecycleOwner, Observer { notes ->
            adapter.setData(notes)
        })
    }


}