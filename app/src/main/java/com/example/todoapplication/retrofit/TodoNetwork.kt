package com.example.todoapplication.retrofit

import com.example.todoapplication.network.model.Location
import com.example.todoapplication.network.model.TodoDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TodoNetwork {

    @GET("todo")
    fun getData():Call<List<TodoDataItem>>
}