package com.example.foodrecipeapp2.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp2.Listeners.RecipeClickListener
import com.example.foodrecipeapp2.R
import com.example.foodrecipeapp2.models.SimilarRecipeResponse
import com.squareup.picasso.Picasso

class SimilarRecipeAdapter(
    private val context: Context,
    private val list: List<SimilarRecipeResponse>?,
    private val listener:RecipeClickListener): RecyclerView.Adapter<SimilarRecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarRecipeViewHolder {
        return SimilarRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipe,parent,false))
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
    //https://img.spoonacular.com/recipes/{ID}-{SIZE}.{TYPE},

    override fun onBindViewHolder(holder: SimilarRecipeViewHolder, position: Int) {
        holder.textView_similar_title.text=list?.get(position)?.title
        holder.textView_similar_title.isSelected=true
        holder.textView_similar_serving.text="${list?.get(position)?.servings} Persons"
        Picasso.get().load("https://img.spoonacular.com/recipes/${list?.get(position)?.id}-556x370.jpg").into(holder.imageView_similar)
        holder.similar_recipe_holder.setOnClickListener{
            listener.onRecipeClicked(list?.get(holder.adapterPosition)?.id.toString())
        }
    }
}
class SimilarRecipeViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    val similar_recipe_holder:CardView=itemView.findViewById(R.id.similar_recipe_holder)
    val textView_similar_title: TextView =itemView.findViewById(R.id.textView_similar_title)
    val imageView_similar:ImageView=itemView.findViewById(R.id.imageView_similar)
    val textView_similar_serving:TextView=itemView.findViewById(R.id.textView_similar_serving)
}