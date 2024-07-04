package com.example.foodrecipeapp2.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp2.R
import com.example.foodrecipeapp2.models.ExtendedIngredient
import com.squareup.picasso.Picasso

class IngredientsAdapter(val context: Context, val list:ArrayList<ExtendedIngredient>?) : RecyclerView.Adapter<IngredientsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        return IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meal_ingredients,parent,false))
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.textView_ingredients_name.text= list?.get(position)?.name
        holder.textView_ingredients_quantity.text=list?.get(position)?.original
        holder.textView_ingredients_quantity.isSelected=true
        holder.textView_ingredients_name.isSelected=true
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/${list?.get(position)?.image}").into(holder.imageView_ingredients)
    }
}
class IngredientsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val textView_ingredients_quantity:TextView = itemView.findViewById(R.id.textView_ingredients_quantity)
    val textView_ingredients_name:TextView = itemView.findViewById(R.id.textView_ingredients_name)
    val imageView_ingredients:ImageView = itemView.findViewById(R.id.imageView_ingredients)
}