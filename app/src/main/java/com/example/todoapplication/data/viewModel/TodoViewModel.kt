package com.example.todoapplication.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.data.TodoDatabase
import com.example.todoapplication.data.models.TodoData
import com.example.todoapplication.data.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel (application: Application): AndroidViewModel(application){
    private val todoDao = TodoDatabase.getDatabase(application).todoDao()
    private val repository: TodoRepository = TodoRepository(todoDao)

     val getAllData: LiveData<List<TodoData>> = repository.getAllData

    fun insertData(todoData: TodoData){
        viewModelScope.launch (Dispatchers.IO) {
            repository.insertData(todoData)
        }
    }

    fun updateData(todoData: TodoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateData(todoData)
        }
    }

    fun deleteItem(todoData: TodoData){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteItem(todoData)
        }
    }
}