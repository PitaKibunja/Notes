package com.example.notes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.R
import com.example.notes.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import net.simplifiedcoding.mynotes.ui.NotesAdapter

class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //recyler manager
        recycler_view_notes.setHasFixedSize(true)
        recycler_view_notes.layoutManager=StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        //get all the notes from the database and display them on the xml
        launch {
            context?.let{
                val notes= NoteDatabase(it).getNoteDao().getAllNotes()
                //in order to display these notes we need a recycler view adapter
                recycler_view_notes.adapter=NotesAdapter(notes)
            }
        }



        //set the onclick listener
        button_add.setOnClickListener{
                val action= HomeFragmentDirections.actionAddNote()
                Navigation.findNavController(it).navigate(action)
        }
    }
}