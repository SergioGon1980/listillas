package com.example.listillas.storage


import android.content.Context
import android.util.Log
import com.example.listillas.config.ConfigService
import com.example.listillas.list.item.Item
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File

class JsonService (val context: Context){
    val folderName = "json"
    val fileExtension = ".json"

    fun createJsonFile (name:String, copyExample:Boolean) {

        // Para que el directorio del fichero JSON sea externo a la aplicacion
        // - context.getExternalFilesDir()
        // Para que el directorio del fichero JSON quede empaquetado con la aplicacion
        // - context.filesDir
        val folder = File(context.filesDir, folderName)
        // No hace falta comprobar si la carpeta existe porque el MKDIR
        // lo hace por defecto
        folder.mkdir()

        val file = File (folder, name + fileExtension)
        file.createNewFile()

        var json = "[]"
        if (copyExample) {
            val gson = GsonBuilder().setPrettyPrinting().create()
            val list = getListFromExample()
            json = gson.toJson(list)
        }


        file.writeText(json)

    }

    fun createFirebaseNode (name:String, copyExample:Boolean) {
        val database =
            Firebase.database("https://sergiogon1980-listillas-default-rtdb.europe-west1.firebasedatabase.app/")
        val reference = database.getReference(name)

        if (copyExample) {
            reference.setValue(getListFromExample())
        } else {
            reference.setValue(emptyList<Item>())
        }
    }

    fun readFileList():List<JsonItem> {
        val files = mutableListOf<JsonItem>()
        val configService = ConfigService (context)
        val defaultFileName = configService.getDefaultFileName()

        val folder = File(context.filesDir,folderName)
        folder.walk().forEach {
            if (!it.isDirectory) {
                val item = JsonItem(
                    it.nameWithoutExtension,
                    it.nameWithoutExtension == defaultFileName
                )
                files.add(item)
            }
        }

        return files
    }

    fun updateFile(){}

    fun deleteFile(json: JsonItem) {
        val folder = File(context.filesDir, folderName)
        val file = File(folder, json.name + fileExtension)
        file.delete()

        if (json.selected) {
            val configService = ConfigService(context)
            configService.setDefaultFileName("")
        }
    }

    fun selectFile (list: List<JsonItem>, item: JsonItem) {
        val configService = ConfigService(context)

        configService.setDefaultFileName(item.name)

        list.forEach {
            it.selected = it.name == item.name
        }
    }

    fun gertSelectedIndex(list: List<JsonItem>): Int{
        return list.indexOfFirst { jsonItem -> jsonItem.selected }
    }

    fun getSelectedFile ():File {
        val configService = ConfigService (context)
        val fileName = configService.getDefaultFileName() + fileExtension
        val folder = File(context.filesDir, folderName)
        return File(folder, fileName)

    }

    private fun getListFromExample(): List<Item> {
        val file = context.assets.open("example.json")
        val json = file.bufferedReader().use { it.readText()}
        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<List<Item>>() {}.type
        val list:List<Item> = gson.fromJson(json, itemType)
        Log.d("debug", "LISTA: " + list)
        return list
    }
}