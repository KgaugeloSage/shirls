package com.example.hotelmanagementapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hotelmanagementapp.retrofit.INodeJS
import com.example.hotelmanagementapp.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_guest.*
import kotlinx.android.synthetic.main.activity_main.*

class GuestActivity : AppCompatActivity() {

    lateinit var myAPI: INodeJS
    var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)

        //Initialise API
        val retrofit = RetrofitClient.instance
        myAPI = retrofit.create(INodeJS::class.java)

        val fullName = intent.getStringExtra("full_name").toString()
        val roomType = intent.getStringExtra("room_type").toString()
        val price = intent.getStringExtra("price").toString()

        infoViewer(fullName,price,roomType)

        logout.setOnClickListener {
            val intent = Intent(this@GuestActivity,MainActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun infoViewer(fullName: String, price: String, roomType: String) {
        welcome.text = "Welcome $fullName"
        price_money.text = price
        type.text = roomType
    }


}