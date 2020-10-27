package com.example.hotelmanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hotelmanagementapp.retrofit.INodeJS
import com.example.hotelmanagementapp.retrofit.RetrofitClient
import com.example.hotelmanagementapp.retrofit.UserModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : AppCompatActivity() {

    lateinit var myAPI: INodeJS
    var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Initialise API
        val retrofit = RetrofitClient.instance
        myAPI = retrofit.create(INodeJS::class.java)

        val errorMassage = "Required"

        register_button.setOnClickListener {
            if (edt_full_name.text?.isEmpty()!!) {
                edt_full_name.error = errorMassage
            }
            if (edt_username.text?.isEmpty()!!) {
                edt_username.error = errorMassage
            }
            if (edt_password.text?.isEmpty()!!) {
                edt_password.error = errorMassage
            }
            if (edt_confirm_password.text?.isEmpty()!!) {
                edt_confirm_password.error = errorMassage
            }
            if (edt_password.text.toString() == edt_confirm_password.text.toString()) {
                register(
                    edt_username.text.toString(),
                    edt_full_name.text.toString(),
                    edt_password.text.toString()
                )
            } else {
                edt_confirm_password.error = "Password Has To Match"
            }

        }
    }

    private fun register(username: String, fullName: String, password: String) {
        val userModel = UserModel(username, password, fullName, "", "","","B")
        val call: Call<UserModel> = myAPI.registerUser(userModel)
        call.enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                Toast.makeText(
                    this@Register,
                    response.body()?.fullName + " Your Account has been created\n\n Login Please",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@Register, MainActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Toast.makeText(this@Register, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

        }

        )
    }


}