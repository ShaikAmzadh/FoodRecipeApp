package com.example.foodrecipeapp2

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp2.Adapters.IngredientsAdapter
import com.example.foodrecipeapp2.Listeners.RecipeDetailsListener
import com.example.foodrecipeapp2.models.RecipeDetailsResponse
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates
@Suppress("DEPRECATION")
class RecipeDetailsActivity : AppCompatActivity() {
    lateinit var id:individualId
    lateinit var mealName:TextView
    lateinit var mealSource:TextView
    lateinit var mealSummary:TextView
    lateinit var mealImage: ImageView
    lateinit var mealIngredients:RecyclerView
    lateinit var manager: RequestManager
    lateinit var dialog: ProgressDialog
    lateinit var ingredientsAdapter: IngredientsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recipe_details)
        findViews()
        val gotId: Int? =intent.getStringExtra("id")?.toInt()
        id=individualId(gotId)
        manager=RequestManager(this)
        manager.getRecipeDetails(recipeDetailsListener, id.id!!)

        dialog=ProgressDialog(this)
        dialog.setTitle("Loading Details...")
        dialog.show()
//        Toast.makeText(this,"${id.id}",Toast.LENGTH_SHORT).show()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun findViews() {
        mealName=findViewById(R.id.textView_meal_name)
        mealSource=findViewById(R.id.textView_meal_source)
        mealSummary=findViewById(R.id.textView_meal_summary)
        mealImage=findViewById(R.id.imageView_meal_image)
        mealIngredients=findViewById(R.id.recycler_meal_ingredients)
    }

    private val recipeDetailsListener=object :RecipeDetailsListener{
        override fun didFetch(response: RecipeDetailsResponse?, message: String?) {
            dialog.dismiss()
            mealName.text=response?.title
            mealSource.text=response?.sourceName
            mealSummary.text=response?.summary
            Picasso.get().load(response?.image).into(mealImage)

            mealIngredients.setHasFixedSize(true)
            mealIngredients.layoutManager = LinearLayoutManager(this@RecipeDetailsActivity,LinearLayoutManager.HORIZONTAL,false)
            ingredientsAdapter=IngredientsAdapter(this@RecipeDetailsActivity,response?.extendedIngredients)
            mealIngredients.adapter=ingredientsAdapter
        }

        override fun didError(message: String?) {
           Toast.makeText(this@RecipeDetailsActivity,"Error message $message",Toast.LENGTH_SHORT)
        }

    }
}