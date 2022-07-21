package com.example.listillas.list

import android.content.Context
import android.util.Log
import com.example.listillas.config.ConfigService
import com.example.listillas.entities.Note
import com.example.listillas.storage.JsonService
import com.example.listillas.list.item.Item
import com.example.listillas.storage.INotesRepository
import com.example.listillas.storage.StorageTypes
import com.example.listillas.storage.firebase.NotesRepositoryFirebase
import com.example.listillas.storage.local.NotesRepositoryLocal
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class ListService constructor(val context: Context) {

    var notes: List<Note>
    var noteRepository: INotesRepository

    private var configService: ConfigService


    init {
        val defaultId: String?
        configService = ConfigService(context)
        when (configService.getSlectedStorage()) {
            StorageTypes.FIREBASE -> {
                noteRepository = NotesRepositoryFirebase(context)
                defaultId = configService.getDefaultFirebaseNode()
            }
            StorageTypes.LOCAL -> {
                noteRepository = NotesRepositoryLocal(context)
                defaultId = configService.getDefaultFileName()
            }
        }

        notes = noteRepository.readNotesById(defaultId!!)
    }
}





