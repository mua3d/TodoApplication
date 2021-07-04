package com.example.todoapplication.retrofit

import com.example.todoapplication.data.models.TodoDataItem
import retrofit2.Call
import retrofit2.http.GET


interface TodoNetwork {

    @GET("todo")
    fun getData():Call<List<TodoDataItem>>
}