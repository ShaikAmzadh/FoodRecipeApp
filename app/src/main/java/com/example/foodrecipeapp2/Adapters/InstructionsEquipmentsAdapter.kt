package com.example.foodrecipeapp2.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp2.R
import com.example.foodrecipeapp2.models.Equipment
import com.example.foodrecipeapp2.models.Ingredient
import com.squareup.picasso.Picasso

class InstructionsEquipmentsAdapter(val context: Context, val list: ArrayList<Equipment>?):RecyclerView.Adapter<InstructionEquipmentsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InstructionEquipmentsViewHolder {
        return InstructionEquipmentsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions_step_items,parent,false))
    }

    override fun getItemCount(): Int {
        return list?.size?:0
    }

    override fun onBindViewHolder(holder: InstructionEquipmentsViewHolder, position: Int) {
        holder.textView_instructions_step_item.text=list?.get(position)?.name
        holder.textView_instructions_step_item.isSelected=true
        Picasso.get().load("${list?.get(position)?.image}").into(holder.imageView_instructions_step_items)
    }
}
class InstructionEquipmentsViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    val imageView_instructions_step_items:ImageView=itemView.findViewById(R.id.imageView_instructions_step_items)
    val textView_instructions_step_item:TextView=itemView.findViewById(R.id.textView_instructions_step_item)
}