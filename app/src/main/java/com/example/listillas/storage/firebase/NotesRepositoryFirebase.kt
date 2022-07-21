package com.example.listillas.storage.firebase

import android.content.Context
import com.example.listillas.entities.Note
import com.example.listillas.storage.INotesRepository

class NotesRepositoryFirebase(val context: Context): INotesRepository {
    override fun createNotes(id: String, copExample: Boolean) {
        TODO("Not yet implemented")
    }

    override fun readnotes(): List<String> {
        TODO("Not yet implemented")
    }

    override fun readNotesById(id: String): List<Note> {
        TODO("Not yet implemented")
    }

    override fun updateNotes(id: String, notes: List<Note>) {
        TODO("Not yet implemented")
    }

    override fun deleteNotes(id: String) {
        TODO("Not yet implemented")
    }
}