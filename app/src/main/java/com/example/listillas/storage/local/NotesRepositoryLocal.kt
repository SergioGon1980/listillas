package com.example.listillas.storage.local

import android.content.Context
import com.example.listillas.config.ConfigService
import com.example.listillas.entities.Note
import com.example.listillas.storage.INotesRepository
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.InputStream

class NotesRepositoryLocal(val context: Context): INotesRepository {

    private val folderName = "json"
    private val fileExtension = ".json"

   //Crear nuevo fichero json
    override fun createNotes(id: String, copyExample: Boolean) {
        // Para que el directorio del fichero JSON sea externo a la aplicacion
        // - context.getExternalFilesDir()
        // Para que el directorio del fichero JSON quede empaquetado con la aplicacion
        // - context.filesDir
        val folder = File(context.filesDir, folderName)
        // No hace falta comprobar si la carpeta existe porque el MKDIR
        // lo hace por defecto
        folder.mkdir()

        val file = File (folder, id + fileExtension)
        file.createNewFile()

        var json = "[]"
        if (copyExample) {
            val example = context.assets.open("example.json")
            val gson = GsonBuilder().setPrettyPrinting().create()
            val list = parseFile(example)
            json = gson.toJson(list)
        }


        file.writeText(json)
    }

    //Recuperar la listas de los ficheros json que tenemos creados
    override fun readnotes(): List<String> {
        val files = mutableListOf<String>()

        val folder = File(context.filesDir,folderName)
        folder.walk().forEach {
            if (!it.isDirectory) {
                files.add(it.nameWithoutExtension)
            }
        }

        return files
    }

    //Recupera el contenido de un fichero json determinado
    override fun readNotesById(id: String): List<Note> {
        val fileName = id + fileExtension
        val folder = File(context.filesDir, folderName)
        val file = File(folder, fileName)

        return parseFile(file.inputStream())
    }

    override fun updateNotes(id: String, notes: List<Note>) {
        TODO("Not yet implemented")
    }

    //Borra un fichero json
    override fun deleteNotes(id: String) {
        val folder = File(context.filesDir, folderName)
        val file = File(folder, id + fileExtension)
        file.delete()

        val configService = ConfigService(context)
        if (configService.getDefaultFileName() == id) {
            configService.setDefaultFileName("")
        }
    }

    private fun parseFile(file: InputStream): List<Note> {
        val json = file.bufferedReader().use { it.readText()}
        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<List<Note>>() {}.type

        return gson.fromJson(json, itemType)
    }
}