package com.example.listillas.storage

import android.content.Context
import com.example.listillas.entities.Note

interface INotesRepository {

    fun createNotes(id: String, copyExample: Boolean)
    fun readnotes ():List<String>
    fun readNotesById(id: String):List<Note>
    fun updateNotes(id: String, notes: List<Note>)
    fun deleteNotes(id: String)

}