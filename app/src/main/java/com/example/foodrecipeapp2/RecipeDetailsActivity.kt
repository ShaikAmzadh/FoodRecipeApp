package com.example.foodrecipeapp2

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp2.Adapters.IngredientsAdapter
import com.example.foodrecipeapp2.Adapters.InstructionsAdapter
import com.example.foodrecipeapp2.Adapters.SimilarRecipeAdapter
import com.example.foodrecipeapp2.Listeners.InstructionsListener
import com.example.foodrecipeapp2.Listeners.RecipeClickListener
import com.example.foodrecipeapp2.Listeners.RecipeDetailsListener
import com.example.foodrecipeapp2.Listeners.SimilarRecipesListener
import com.example.foodrecipeapp2.models.InstructionsResponse
import com.example.foodrecipeapp2.models.RecipeDetailsResponse
import com.example.foodrecipeapp2.models.SimilarRecipeResponse
import com.squareup.picasso.Picasso

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
    lateinit var similarMeals:RecyclerView
    lateinit var similarRecipeAdapter: SimilarRecipeAdapter
    lateinit var recycler_meal_instructions:RecyclerView
    lateinit var back_btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recipe_details)
        findViews()
        val gotId: Int? =intent.getStringExtra("id")?.toInt()
        id=individualId(gotId)
        manager=RequestManager(this)
        manager.getRecipeDetails(recipeDetailsListener, id.id!!)
        manager.getSimilarRecipes(similarRecipesListener, id.id ?: 0)
        manager.getInstructions(instructionsListener,id.id?:0)
        dialog=ProgressDialog(this)
        dialog.setTitle("Loading Details...")
        dialog.show()


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
        similarMeals=findViewById(R.id.recycler_meal_similar)
        recycler_meal_instructions=findViewById(R.id.recycler_meal_instructions)
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
           Toast.makeText(this@RecipeDetailsActivity,"Error message $message",Toast.LENGTH_SHORT).show()
        }

    }
    private val similarRecipesListener=object : SimilarRecipesListener{
        override fun didFetch(response: List<SimilarRecipeResponse>?, message: String) {
            similarMeals.setHasFixedSize(true)
            similarMeals.layoutManager=LinearLayoutManager(this@RecipeDetailsActivity,LinearLayoutManager.HORIZONTAL,false)
            similarRecipeAdapter=SimilarRecipeAdapter(this@RecipeDetailsActivity,response,recipeClickListener)
            similarMeals.adapter=similarRecipeAdapter
        }

        override fun didError(message: String?) {
            Toast.makeText(this@RecipeDetailsActivity,"$message",Toast.LENGTH_SHORT).show()
        }

    }
   private val recipeClickListener=object:RecipeClickListener{
       override fun onRecipeClicked(id: String?) {
           startActivity(Intent(this@RecipeDetailsActivity,RecipeDetailsActivity::class.java).putExtra("id","$id"))
       }

   }
    private val instructionsListener= object : InstructionsListener {
        override fun didFetch(response: List<InstructionsResponse>?, message: String) {
            recycler_meal_instructions.setHasFixedSize(true)
            recycler_meal_instructions.layoutManager=LinearLayoutManager(this@RecipeDetailsActivity,LinearLayoutManager.HORIZONTAL,false)
            recycler_meal_instructions.adapter=InstructionsAdapter(this@RecipeDetailsActivity,response)
        }

        override fun didError(message: String?) {
            Toast.makeText(this@RecipeDetailsActivity,message,Toast.LENGTH_SHORT).show()
        }

    }
}