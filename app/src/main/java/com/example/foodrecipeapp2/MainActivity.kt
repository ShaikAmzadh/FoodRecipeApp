package com.example.foodrecipeapp2

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import android.widget.Toast.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp2.Adapters.RandomRecipeAdapter
import com.example.foodrecipeapp2.Listeners.RandomRecipeResponseListener
import com.example.foodrecipeapp2.Listeners.RecipeClickListener
import com.example.foodrecipeapp2.models.RandomRecipeApiResponse

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    lateinit var dialog:ProgressDialog
    lateinit var manager: RequestManager
    lateinit var randomRecipeAdapter: RandomRecipeAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var spinner: Spinner
    var tags:ArrayList<String> = arrayListOf()
    lateinit var searchView:SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        dialog= ProgressDialog(this)
        dialog.setTitle("Loading...")

        searchView=findViewById(R.id.searchView_home)
        searchView.queryHint="Search your recipes..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                tags.clear()
                tags.add(query)
                manager.getRandomrecipes(randomRecipeResponseListener,tags)
                dialog.show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle text change
                return false
            }
        })


        spinner=findViewById(R.id.spinner_tags)
        val arrayAdapter=ArrayAdapter.createFromResource(this,R.array.tags,R.layout.spinner_text)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text)
        spinner.adapter=arrayAdapter
        spinner.onItemSelectedListener=spinnerSelectedListener


        manager= RequestManager(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private val randomRecipeResponseListener = object : RandomRecipeResponseListener {




        override fun didFetch(response: RandomRecipeApiResponse?, message: String?) {
            dialog.dismiss()
            recyclerView=findViewById(R.id.recycler_random)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 1)
            randomRecipeAdapter= RandomRecipeAdapter(this@MainActivity,response?.recipes,recipeClickListener)
            recyclerView.adapter=randomRecipeAdapter
        }

        override fun didError(message: String?) {
            Toast.makeText(this@MainActivity,message, LENGTH_SHORT).show()
        }
    }
    private val spinnerSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            tags.clear()
            tags.add(parent?.selectedItem.toString())
            manager.getRandomrecipes(randomRecipeResponseListener,tags)
            dialog.show()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            // Your code here
        }
    }
    private val recipeClickListener:RecipeClickListener = object : RecipeClickListener {
        override fun onRecipeClicked(id: String?) {
            startActivity(Intent(this@MainActivity,RecipeDetailsActivity::class.java)
                .putExtra("id",id))
        }
    }

}