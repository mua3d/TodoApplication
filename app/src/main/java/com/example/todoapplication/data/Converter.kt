package com.example.todoapplication.data

import com.example.todoapplication.data.models.Priority
import androidx.room.TypeConverter


//this class is for converting priority to string when writing to database and from string back to priority when reading from database because the database "Room" only take primitive data types but not entities
class Converter {

    //from priority to string
    @TypeConverter
    fun fromPriority(priority: Priority): String{
        return priority.name
    }

    //from string to priority
    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }

}