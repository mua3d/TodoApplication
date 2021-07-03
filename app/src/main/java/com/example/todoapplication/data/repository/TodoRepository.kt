package com.example.todoapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.todoapplication.data.TodoDao
import com.example.todoapplication.data.models.TodoData

class TodoRepository(private val todoDao: TodoDao) {
    val getAllData: LiveData<List<TodoData>> = todoDao.getAllData()

    suspend fun insertData(todoData: TodoData){
        todoDao.insertDate(todoData)
    }

    suspend fun updateData(todoData: TodoData){
        todoDao.updateData(todoData)
    }

    suspend fun deleteItem(todoData: TodoData){
        todoDao.deleteItem((todoData))
    }
}