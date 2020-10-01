package com.example.hotelmanagementapp.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {
    private var ourInstance: Retrofit? =null
    val instance:Retrofit
        get(){
            if(ourInstance==null)
                ourInstance = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/KgaugeloSage/database-to-test/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())//changed because i used gson database
                    .build()
            return ourInstance!!
        }




}