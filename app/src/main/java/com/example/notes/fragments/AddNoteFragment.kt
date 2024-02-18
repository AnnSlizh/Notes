package com.example.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.notes.R
import com.example.notes.database.Note
import com.example.notes.database.viewModel.NoteViewModel
import com.example.notes.databinding.FragmentAddNoteBinding
import java.util.Calendar


class AddNoteFragment : Fragment() {

    private lateinit var saveButton: Button

    private lateinit var noteViewModel: NoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveButton = view.findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            saveNote(view)
        }
    }

    private fun saveNote(view: View) {
        val title = view.findViewById<EditText>(R.id.addNoteTitle).text
        val description = view.findViewById<EditText>(R.id.addNoteDesc).text

        if (description.isEmpty() && title.isEmpty()) {
            Toast.makeText(context, "Add your note!", Toast.LENGTH_SHORT).show()
        } else {

            val note = Note(0, title.toString(), description.toString(), Calendar.getInstance().time )

            noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
            noteViewModel.addNote(note)

            Toast.makeText(context, "Note saved", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).navigate(R.id.action_addNoteFragment_to_homeFragment)
        }
    }

}