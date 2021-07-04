package com.example.todoapplication.fragments

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.example.todoapplication.R
import com.example.todoapplication.data.models.Priority

//this class is for functions that are shared between fragments

class SharedViewModel(application: Application): AndroidViewModel(application) {
    val listener: AdapterView.OnItemSelectedListener = object:
        AdapterView.OnItemSelectedListener{
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
           //change priority spinner items color when selected
           when(position){
               // 0 represent the highest priority, 1 for medium priority and 2 for low priority
               0 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))}
               1 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))}
               2 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))}
           }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

    }


     fun verifyDataFromUser(title: String, description: String): Boolean {
        return if(TextUtils.isEmpty(title)|| TextUtils.isEmpty(description)){
            false
        }else !(title.isEmpty()|| description.isEmpty())
    }

      fun parsePriority(priority: String): Priority {
        return when (priority){
            "High priority" -> {
                Priority.HIGH}
            "Medium priority" -> {
                Priority.MEDIUM}
            "Low priority" -> {
                Priority.LOW}
            else -> Priority.LOW

        }
    }



    fun parsePriorityToInt(priority: Priority): Int{
        return when(priority){
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }

    }

    

}