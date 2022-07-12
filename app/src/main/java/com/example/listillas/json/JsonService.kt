package com.example.listillas.json

import android.content.ClipData
import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.example.listillas.list.ListService
import com.example.listillas.list.item.Item
import org.json.JSONArray
import java.io.File

class JsonService (val context: Context){

    fun createJsonFile (name:String, copyExample:Boolean) {
        Log.d("debug","Crea un nuevo JSON: " + name)
        Log.d("debug","Crea a partir del ejemplo: " + copyExample.toString())
        //TODO crear un nuevo fichero JSON

        var list = mutableListOf<Item>()

        if (copyExample) {
            list = getListFromExample()
        }
        // Para que el directorio del fichero JSON sea externo a la aplicacion
        // - context.getExternalFilesDir()
        // Para que el directorio del fichero JSON quede empaquetado con la aplicacion
        // - context.filesDir
        val folder = File(context.filesDir, "json")
            // No hace falta comprobar si la carpeta existe porque el MKDIR
            // lo hace por defecto
            folder.mkdir()

        val file = File (folder, name + ".json")
        file.createNewFile()





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