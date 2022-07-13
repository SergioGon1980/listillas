package com.example.listillas.json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listillas.R
import com.example.listillas.databinding.ActivityJsonBinding
import com.example.listillas.menu.MenuHandler

class JsonActivity : AppCompatActivity() {

    private var _binding: ActivityJsonBinding? = null
    private val binding get() = _binding!!

    private lateinit var jsonListAdapter: JsonListAdapter
    private lateinit var jsonList: List<JsonItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityJsonBinding.inflate(layoutInflater)
        setContentView(binding.root)

/*        val handler: (JsonItem) -> = JsonItem {
            jsonItem: JsonItem ->
            {
                Log.d("debug", "ITEM" + jsonItem.name)
            }
        }*/

        /*jsonList = JsonService(this).getFileList()
        jsonListAdapter = JsonListAdapter(jsonList, handler)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager (this)
        binding.fileListContainer.layoutManager = layoutManager
        binding.fileListContainer.adapter = jsonListAdapter*/

        binding.createJsonBtn.setOnClickListener(){
            createJsonHandler()
        }





    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var menuHandler = MenuHandler(this,"Json")
        menuHandler.itemHandler(item)
        if (menuHandler.intent != null) {
            startActivity(menuHandler.intent)
        }
        return super.onOptionsItemSelected(item)
    }

    fun createJsonHandler() {
        val jsonService = JsonService(this)
        jsonService.createJsonFile(
            binding.jsonName.text.toString(),
            binding.exampleCheck.isChecked
        )
    }
}