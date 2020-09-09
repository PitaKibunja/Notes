package com.example.notes.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.notes.R
import com.example.notes.db.Note
import com.example.notes.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch


class AddNoteFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_done.setOnClickListener{view->
            val noteTitle=edit_text_title.text.toString().trim()
            val noteBody=edit_text_note.text.toString().trim()

            //check if the fields are empty. if so show an alert
            //reset the onclicklistener to those fields.
            if (noteTitle.isEmpty()){
                edit_text_title.error="title required"
                edit_text_title.requestFocus()
                return@setOnClickListener
            }
            if(noteBody.isEmpty()){
                edit_text_note.error="note required"
                edit_text_note.requestFocus()
                return@setOnClickListener
            }

            launch {
                val note=Note(noteTitle,noteBody)
                context?.let {
                    NoteDatabase(it).getNoteDao().addNote(note)
                    it.toast("Note Saved")
                    val action=AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view).navigate(action)
                }

            }


        }
    }

}