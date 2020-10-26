package com.example.hotelmanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_booking.*

class ViewBooking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_booking)

        btn_cancel_booking.setOnClickListener {
            val intent = Intent(this@ViewBooking,GuestMain::class.java)
            startActivity(intent)
        }

        btn_back.setOnClickListener {
            val intent = Intent(this@ViewBooking,GuestMain::class.java)
            startActivity(intent)
        }
    }
}