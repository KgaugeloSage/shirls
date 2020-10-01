package com.example.hotelmanagementapp.retrofit

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface INodeJS {


    @POST("register")
    fun registerUser(@Body userModel: UserModel) : Call<UserModel>

    @GET("login/{id}")
    fun loginUser(@Path("id") username: String
    ) : Observable<UserModel>

}