package com.example.notesapplication

import android.content.Intent
import android.os.Bundle
import android.service.controls.actions.FloatAction
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {
    lateinit var  notesRV:RecyclerView
    lateinit var  addFAB :FloatingActionButton
    lateinit var  viewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesRV = findViewById(R.id.idRVNotes)
        addFAB = findViewById(R.id.idFABAddNote)
        notesRV.layoutManager = LinearLayoutManager(this)

        val noteRVAdapter = NoteRVAdapter(this, this, this)
         notesRV.adapter = noteRVAdapter
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)
        viewModel.allNotes.observe(this, Observer{list->
            list?.let{
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener{
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDeleteIconClick(note: Note) {
         viewModel.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle}Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
         intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.notDescription)
        intent.putExtra("noteID", note.id)
        startActivity(intent)
        this.finish()

    }
}