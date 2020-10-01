package com.example.hotelmanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hotelmanagementapp.retrofit.INodeJS
import com.example.hotelmanagementapp.retrofit.RetrofitClient
import com.example.hotelmanagementapp.retrofit.UserModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.util.HalfSerializer.onError
import io.reactivex.internal.util.HalfSerializer.onNext
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Flow
import java.util.function.Consumer

class MainActivity : AppCompatActivity() {

    lateinit var myAPI:INodeJS
    var compositeDisposable = CompositeDisposable()

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
                    Toast.makeText(this@MainActivity, "LoggedIn successfully as "+ it.fullName, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Incorrect Password", Toast.LENGTH_SHORT).show()
                }
            },{
                Toast.makeText(this@MainActivity, "UserName Does Not Exist", Toast.LENGTH_SHORT).show()
            }))

//        compositeDisposable.add(myAPI.loginUser(username, password)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe{ message : UserModel ->
//                if(message.password.equals(password)){
//                    Toast.makeText(this@MainActivity, "Login successful", Toast.LENGTH_SHORT.show())
//                }
//                else{
//                Toast.makeText(this@MainActivity, message.fullName, Toast.LENGTH_SHORT.show())
//                }
//            })


    }

}






