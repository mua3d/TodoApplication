package com.example.mvvmkotlinexample.retrofit

import com.example.todoapplication.BuildConfig
import com.example.todoapplication.retrofit.TodoNetwork
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    const val MainServer = "https://60db261d801dcb0017290ed9.mockapi.io/"

    val retrofitClient: Retrofit.Builder by lazy {

        val levelType: HttpLoggingInterceptor.Level
        if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            levelType = Level.BODY else levelType = Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val okhttpClient = OkHttpClient.Builder()
        okhttpClient.addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl(MainServer)
            .client(okhttpClient.build())


            .addConverterFactory(GsonConverterFactory.create())
    }


    val apiInterface: TodoNetwork by lazy {
        retrofitClient
            .build()
            .create(TodoNetwork::class.java)
    }
}