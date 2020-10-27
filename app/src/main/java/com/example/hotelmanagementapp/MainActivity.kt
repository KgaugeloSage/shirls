package com.example.hotelmanagementapp

//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.hotelmanagementapp.retrofit.INodeJS
import com.example.hotelmanagementapp.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_guest.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myAPI:INodeJS
    var compositeDisposable = CompositeDisposable()
    lateinit var setListener : DatePickerDialog.OnDateSetListener


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialise API
        val retrofit = RetrofitClient.instance
        myAPI = retrofit.create(INodeJS::class.java)

        login_button.setOnClickListener{
            login(edt_username.text.toString(), edt_password.text.toString())
        }

        register_button.setOnClickListener{
            val intent = Intent(applicationContext, Register::class.java)
            startActivity(intent)
        }

    }

    private fun login(username: String, password: String) {
        compositeDisposable.add(myAPI.loginUser(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.password.equals(password)) {
                    Toast.makeText(
                        this@MainActivity,
                        "Logged In successfully as " + it.fullName,
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@MainActivity, GuestMain::class.java)
                    intent.putExtra("full_name", it.fullName)
                    intent.putExtra("room_type", it.roomType)
                    intent.putExtra("price", it.price)
                    intent.putExtra("check_in",it.checkIn)
                    intent.putExtra("check_out",it.checkOut)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@MainActivity, "Incorrect Password", Toast.LENGTH_SHORT)
                        .show()
                }
            }, {
                Toast.makeText(this@MainActivity, "UserName Does Not Exist", Toast.LENGTH_SHORT)
                    .show()
            })
        )



    }

}






