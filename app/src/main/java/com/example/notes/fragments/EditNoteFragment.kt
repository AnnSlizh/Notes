package com.example.notes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notes.R
import com.example.notes.database.Note
import com.example.notes.database.viewModel.NoteViewModel
import com.example.notes.databinding.FragmentEditNoteBinding
import com.example.notes.databinding.FragmentHomeBinding
import java.util.Calendar


class EditNoteFragment : Fragment() {

    private lateinit var currentNote: Note

    private  val editNoteArgs: EditNoteFragmentArgs by navArgs<EditNoteFragmentArgs>()

    private lateinit var saveButton: Button

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentNote = editNoteArgs.currentNote

        view.findViewById<EditText>(R.id.editNoteTitle).setText(currentNote.title)
        view.findViewById<EditText>(R.id.editNoteDesc).setText(currentNote.description)

        saveButton = view.findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            saveNote(view)
        }
    }

    private fun saveNote(view: View) {

        val title = view.findViewById<EditText>(R.id.editNoteTitle).text.toString()
        val description = view.findViewById<EditText>(R.id.editNoteDesc).text.toString()

        if (description.isEmpty() && title.isEmpty()) {
            Toast.makeText(context, "Add your note!", Toast.LENGTH_SHORT).show()
        } else {

            val note =
                Note(currentNote.id, title, description, Calendar.getInstance().time)

            noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
            noteViewModel.updateNote(note)

            Toast.makeText(context, "Note updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).navigate(R.id.action_editNoteFragment_to_homeFragment)
        }
    }


}