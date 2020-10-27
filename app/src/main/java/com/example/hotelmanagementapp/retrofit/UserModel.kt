package com.example.hotelmanagementapp.retrofit

import com.google.gson.annotations.SerializedName

data class UserModel(@SerializedName("id")val username: String,@SerializedName("password") val password: String,@SerializedName("full_name") val fullName: String,@SerializedName("room_type") val roomType: String,@SerializedName("price") val price: String,@SerializedName("check_in") val checkIn: String,@SerializedName("check_out") val checkOut: String)
//data class UserModel(@SerializedName("userId")val userId: String,@SerializedName("id") val id : String,@SerializedName("title") val title : String,@SerializedName("body") val body : String)
