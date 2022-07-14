package com.example.listillas.json


import android.content.Context
import android.util.Log
import com.example.listillas.list.ListService
import com.example.listillas.list.item.Item
import com.google.gson.GsonBuilder
import org.json.JSONArray
import java.io.File

class JsonService (val context: Context){
    val folderName = "json"
    val fileExtension = ".json"

    fun createJsonFile (name:String, copyExample:Boolean) {
        //TODO crear un nuevo fichero JSON

        var list = mutableListOf<Item>()

        if (copyExample) {
            list = getListFromExample()
        }
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

        val gson = GsonBuilder().setPrettyPrinting().create()
        val json = gson.toJson(list)
        file.writeText(json)
    }

    fun readFileList():List<JsonItem> {
        val files = mutableListOf<JsonItem>()

        val folder = File(context.filesDir,folderName)
        folder.walk().forEach {
            if (!it.isDirectory) {
                val item = JsonItem(it.nameWithoutExtension)
                files.add(item)
            }
        }

        return files
    }

    fun updateFile(jsonFile:JsonFile){}

    fun deleteFile(fileName: String) {
        val folder = File(context.filesDir, folderName)
        val file = File(folder, fileName + fileExtension)
        file.delete()
    }

    fun selectFile (list: List<JsonItem>, item: JsonItem) {
        list.forEach {
            it.selected = it.name == item.name
        }
    }

    fun gertSelectedIndex(list: List<JsonItem>): Int{
        return list.indexOfFirst { jsonItem -> jsonItem.selected }
    }

    private fun getListFromExample():MutableList<Item> {
        val file = context.assets.open("example.json")
        val jsonArray = JSONArray(file.bufferedReader().use {
            it.readText()
        })

        val list = ListService(context).parseJson(jsonArray)

        return list
    }
}