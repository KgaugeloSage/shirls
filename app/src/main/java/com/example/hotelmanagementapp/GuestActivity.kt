package com.example.hotelmanagementapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.hotelmanagementapp.retrofit.INodeJS
import com.example.hotelmanagementapp.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_guest.*
import kotlinx.android.synthetic.main.activity_guest.view.*
import kotlinx.android.synthetic.main.activity_main.*

class GuestActivity : AppCompatActivity() {

    lateinit var myAPI: INodeJS
    var compositeDisposable = CompositeDisposable()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)

        //Initialise API
        val retrofit = RetrofitClient.instance
        myAPI = retrofit.create(INodeJS::class.java)


        btn_back.setOnClickListener {
            val intent = Intent(this@GuestActivity,GuestMain::class.java)
            startActivity(intent)
        }

        btn_change_check_in.setOnClickListener {
            datePicker(check_in_Date)
            btn_change_check_in.visibility = View.GONE
            check_in_Date.visibility = View.VISIBLE
        }

        btn_change_check_out.setOnClickListener {
            datePicker(check_out_Date)
            btn_change_check_out.visibility = View.GONE
            check_out_Date.visibility = View.VISIBLE
        }

        btn_room_change.setOnClickListener {
            roomlist.visibility = View.VISIBLE
            btn_room_change.visibility = View.GONE
        }

      selected()
    }

    private fun selected() {
        roomlist.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            @SuppressLint("SetTextI18n")
            override fun onItemSelected(adpdterView: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (adpdterView?.getItemAtPosition(position).toString()=="Select Room"){
                    Toast.makeText(this@GuestActivity,"Select Room", Toast.LENGTH_LONG).show()
                }else{

                    type.text = adpdterView?.getItemAtPosition(position).toString()
                    type.visibility = View.VISIBLE
                    roomlist.visibility = View.GONE
                    Toast.makeText(this@GuestActivity,"Select "+adpdterView?.getItemAtPosition(position),Toast.LENGTH_LONG).show()
                    if (adpdterView?.getItemAtPosition(position).toString()=="Standard Room"){
                        price_money.text = "R500"
                    }else if(adpdterView?.getItemAtPosition(position).toString()=="Premium Room"){
                        price_money.text = "R1000"
                    } else if (adpdterView?.getItemAtPosition(position).toString()=="Standard Suite"){
                        price_money.text = "R2000"
                    } else if (adpdterView?.getItemAtPosition(position).toString()=="Premium Suite"){
                        price_money.text = "R4000"
                    }

                    price_money.visibility = View.VISIBLE
                }



            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun datePicker(txt : TextView){
        val calendar : Calendar = Calendar.getInstance()
        val year : Int = calendar.get(Calendar.YEAR)
        val month : Int = calendar.get(Calendar.MONTH)
        val day : Int = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this@GuestActivity,
            DatePickerDialog.OnDateSetListener { view : DatePicker, year : Int, month : Int, dayOfMonth : Int ->
                val realMonth = month +1
                val date = "$dayOfMonth/$realMonth/$year"
                txt.text = date
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }


}