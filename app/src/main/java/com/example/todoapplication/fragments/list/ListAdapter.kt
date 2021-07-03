package com.example.todoapplication.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.R
import com.example.todoapplication.data.models.Priority
import com.example.todoapplication.data.models.TodoData

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>(){
    var dataList = emptyList<TodoData>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        //inflate "row_layout" which will contain "to do" information
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //this will change the title and description of our itemView which is in raw_layout dynamically to what we have in our dataList
        holder.itemView.findViewById<TextView>(R.id.title_txt).text = dataList[position].title
        holder.itemView.findViewById<TextView>(R.id.description_txt).text = dataList[position].description
        holder.itemView.findViewById<ConstraintLayout>(R.id.row_background).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }

        when(dataList[position].priority){
            Priority.HIGH ->  holder.itemView.findViewById<CardView>(R.id.priorities_indicator).setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.red))
            Priority.MEDIUM ->  holder.itemView.findViewById<CardView>(R.id.priorities_indicator).setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.yellow))
            Priority.LOW ->  holder.itemView.findViewById<CardView>(R.id.priorities_indicator).setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))

        }


    }

    fun setData(todoData: List<TodoData>){
        this.dataList = todoData
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return dataList.size
    }
}