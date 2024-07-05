package com.example.foodrecipeapp2.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp2.R
import com.example.foodrecipeapp2.models.Step

class InstructionStepAdapter(val context: Context, val list: ArrayList<Step>?) :RecyclerView.Adapter<InstructionStepViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionStepViewHolder {
        return InstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions_steps,parent,false))
    }

    override fun getItemCount(): Int {
        return list?.size?:0
    }

    override fun onBindViewHolder(holder: InstructionStepViewHolder, position: Int) {
        holder.textView_instructions_step_number.text= list?.get(position)?.number.toString()
        holder.textView_instructions_step_title.text=list?.get(position)?.step
        holder.recycler_instructions_ingredients.setHasFixedSize(true)
        holder.recycler_instructions_ingredients.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        holder.recycler_instructions_ingredients.adapter=InstructionsIngredientsAdapter(context,list?.get(position)?.ingredients)
        holder.recycler_instructions_equipments.setHasFixedSize(true)
        holder.recycler_instructions_equipments.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        holder.recycler_instructions_equipments.adapter=InstructionsEquipmentsAdapter(context,list?.get(position)?.equipment)
    }
}
class InstructionStepViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    val textView_instructions_step_number:TextView=itemView.findViewById(R.id.textView_instructions_step_number)
    val textView_instructions_step_title:TextView=itemView.findViewById(R.id.textView_instructions_step_title)
    val recycler_instructions_equipments:RecyclerView=itemView.findViewById(R.id.recycler_instructions_equipments)
    val recycler_instructions_ingredients:RecyclerView=itemView.findViewById(R.id.recycler_instructions_ingredients)
}