package com.example.hotelmanagementapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_view_booking.*

class ViewBooking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_booking)

        val fullName :String = intent.getStringExtra("full_name").toString()
        type.text = intent.getStringExtra("room_type").toString()
        price_money.text = intent.getStringExtra("price").toString()
        check_in_Date.text= intent.getStringExtra("check_in").toString()
        check_out_Date.text = intent.getStringExtra("check_out").toString()

        btn_cancel_booking.setOnClickListener {
                // build alert dialog
                val dialogBuilder = AlertDialog.Builder(this)

                // set message of alert dialog
                dialogBuilder.setMessage("Are You Sure You Want To Cancel Booking ?")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Proceed", DialogInterface.OnClickListener {
                            dialog, id ->
                        val intent = Intent(this@ViewBooking, GuestMain::class.java)
                        intent.putExtra("full_name", fullName)
                        startActivity(intent)
                        finish()
                    })
                    // negative button text and action
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                            dialog, id -> dialog.cancel()
                    })

                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle("Cancel Booking")
                // show alert dialog
                alert.show()
        }


    }
}

