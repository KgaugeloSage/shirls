package com.example.hotelmanagementapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_guest.*
import kotlinx.android.synthetic.main.activity_guest.welcome
import kotlinx.android.synthetic.main.activity_guest_main.*

class GuestMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_main)
        val fullName = intent.getStringExtra("full_name").toString()
//        val roomType = intent.getStringExtra("room_type").toString()
//        val price = intent.getStringExtra("price").toString()

        infoViewer(fullName)

        btn_view_bookings.setOnClickListener {
            val intent = Intent(this@GuestMain, ViewBooking::class.java)
            startActivity(intent)
        }

        btn_update_booking.setOnClickListener {
            val intent = Intent(this@GuestMain, GuestActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun infoViewer(fullName: String) {
        welcome.text = "Welcome\n $fullName"
    }
}