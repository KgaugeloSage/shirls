package com.example.hotelmanagementapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_guest.welcome
import kotlinx.android.synthetic.main.activity_guest_main.*

class GuestMain : AppCompatActivity() {

    var savedName : String = ""

    val SHARED_PREFS : String = "shared_prefs"
    val NAME : String = "name"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_main)
        val fullName = intent.getStringExtra("full_name").toString()
        val roomType = intent.getStringExtra("room_type").toString()
        val price = intent.getStringExtra("price").toString()
        val checkIn = intent.getStringExtra("check_in").toString()
        val checkOut = intent.getStringExtra("check_out").toString()

        savedName = fullName

        btn_view_bookings.setOnClickListener {
            val intent = Intent(this@GuestMain, ViewBooking::class.java)
            intent.putExtra("full_name", fullName)
            intent.putExtra("room_type", roomType)
            intent.putExtra("price", price)
            intent.putExtra("check_in",checkIn)
            intent.putExtra("check_out", checkOut)
            startActivity(intent)
        }

        btn_update_booking.setOnClickListener {
            val intent = Intent(this@GuestMain, GuestActivity::class.java)
            intent.putExtra("full_name", fullName)
            intent.putExtra("room_type", roomType)
            intent.putExtra("price", price)
            intent.putExtra("check_in",checkIn)
            intent.putExtra("check_out", checkOut)
            saveName(fullName)
            startActivity(intent)
        }

    }

    fun saveName(name:String){
        val sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()

        editor.putString(NAME, name)
        Log.e("SAVES","Save called")
    }

    fun loadName(){
        val sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        savedName = sharedPreferences.getString(NAME,savedName).toString()
        Log.e("LOADS","Load Called")

    }

    override fun onPause() {
        super.onPause()
        saveName(savedName)
        Log.e("Pause","onPause Called")
    }

    @SuppressLint("SetTextI18n")
    override fun onRestart() {
        super.onRestart()
        Log.e("OnRestart","OnRestart Called")
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        loadName()
        welcomeMain.text = "Welcome\n$savedName"
        Log.e("OnStart","OnStart Called")

    }
}