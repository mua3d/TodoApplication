package com.example.mvvmkotlinexample.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvmkotlinexample.retrofit.RetrofitClient
import com.example.todoapplication.data.models.TodoDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val serviceSetterGetter = MutableLiveData<TodoDataItem>()

    fun getServicesApiCall(): MutableLiveData<TodoDataItem> {

        val call = RetrofitClient.apiInterface.getData()

        call.enqueue(object : Callback<List<TodoDataItem>?> {
            override fun onResponse(
                call: Call<List<TodoDataItem>?>,
                response: Response<List<TodoDataItem>?>
            ) {

            }

            override fun onFailure(call: Call<List<TodoDataItem>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return serviceSetterGetter
    }
}