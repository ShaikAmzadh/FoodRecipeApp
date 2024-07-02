package com.example.foodrecipeapp2.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp2.R
import com.example.foodrecipeapp2.models.Recipe
import com.squareup.picasso.Picasso

class RandomRecipeAdapter(var context: Context, var list: ArrayList<Recipe>?) :
    RecyclerView.Adapter<RandomRecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomRecipeViewHolder {
        return RandomRecipeViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RandomRecipeViewHolder, position: Int) {
        holder.textView_title.text = list?.get(position)?.title
        holder.textView_title.isSelected = true
        holder.textView_likes.text = list?.get(position)?.aggregateLikes.toString() + " Likes"
        holder.textView_servings.text = list?.get(position)?.servings.toString() + " Serviings"
        holder.textView_time.text = list?.get(position)?.readyInMinutes.toString() + " Minutes"
        Picasso.get().load(list?.get(position)?.image).into(holder.imageView_food)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
}

class RandomRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var random_list_container: CardView = itemView.findViewById(R.id.random_list_container)
    var textView_title: TextView = itemView.findViewById(R.id.textView_title)
    var textView_servings: TextView = itemView.findViewById(R.id.textView_servings)
    var textView_likes: TextView = itemView.findViewById(R.id.textView_likes)
    var textView_time: TextView = itemView.findViewById(R.id.textView_time)
    var imageView_food: ImageView = itemView.findViewById(R.id.imageView_food)
}
