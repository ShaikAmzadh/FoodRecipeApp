package com.example.foodrecipeapp2.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipeapp2.R
import com.example.foodrecipeapp2.models.InstructionsResponse

class InstructionsAdapter(val context: Context, val list: List<InstructionsResponse>?) :RecyclerView.Adapter<InstructionsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionsViewHolder {
        return InstructionsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions,parent,false))
    }

    override fun getItemCount(): Int {
        return list?.size?:0
    }

    override fun onBindViewHolder(holder: InstructionsViewHolder, position: Int) {
        holder.textView_instruction_name.text=list?.get(position)?.name
        holder.recycler_instruction_steps.setHasFixedSize(true)
        holder.recycler_instruction_steps.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        holder.recycler_instruction_steps.adapter=InstructionStepAdapter(context,list?.get(position)?.steps)

    }
}
class InstructionsViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    val textView_instruction_name:TextView = itemView.findViewById(R.id.textView_instruction_name)
    val recycler_instruction_steps:RecyclerView=itemView.findViewById(R.id.recycler_instruction_steps)
}