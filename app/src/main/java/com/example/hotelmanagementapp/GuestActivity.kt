package com.example.hotelmanagementapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.DialogInterface
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
import androidx.appcompat.app.AlertDialog
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
    var dateCheckInSave : String = ""
    var dateCheckOutSave : String = ""
    var priceSave: String = ""
    var roomTypeSave : String = ""

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)

        //Initialise API
        val retrofit = RetrofitClient.instance
        myAPI = retrofit.create(INodeJS::class.java)

        val fullName :String = intent.getStringExtra("full_name").toString()


        btn_save.setOnClickListener {



            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)

            // set message of alert dialog
            dialogBuilder.setMessage("Are You Sure ?")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Yes", DialogInterface.OnClickListener {
                        dialog, id ->

                    val intent = Intent(this@GuestActivity,GuestMain::class.java)
                    intent.putExtra("full_name", fullName)
                    intent.putExtra("room_type", roomTypeSave)
                    intent.putExtra("price", priceSave)
                    intent.putExtra("check_in", dateCheckInSave)
                    intent.putExtra("check_out", dateCheckOutSave)
                    startActivity(intent)


                    finish()
                })
                // negative button text and action
                .setNegativeButton("No", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Make/Change Booking")
            // show alert dialog
            alert.show()
        }

        btn_change_check_in.setOnClickListener {
            datePickerCheckIn(check_in_Date)
            btn_change_check_in.visibility = View.GONE
            check_in_Date.visibility = View.VISIBLE
        }

        btn_change_check_out.setOnClickListener {
            datePickerCheckOut(check_out_Date)
            btn_change_check_out.visibility = View.GONE
            check_out_Date.visibility = View.VISIBLE
        }

        btn_room_change.setOnClickListener {
            roomlist.visibility = View.VISIBLE
            btn_room_change.visibility = View.GONE
        }

      selected()
    }

    private fun selected(){
        roomlist.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            @SuppressLint("SetTextI18n")
            override fun onItemSelected(adpdterView: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (adpdterView?.getItemAtPosition(position).toString()=="Select Room"){
                    Toast.makeText(this@GuestActivity,"Select Room", Toast.LENGTH_LONG).show()
                }else{

                    type.text = adpdterView?.getItemAtPosition(position).toString()
                    type.visibility = View.VISIBLE
                    roomlist.visibility = View.GONE
                    Toast.makeText(this@GuestActivity,"Selected "+adpdterView?.getItemAtPosition(position),Toast.LENGTH_LONG).show()
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
                    priceSave = price_money.text.toString()
                    roomTypeSave = adpdterView?.getItemAtPosition(position).toString()
                }



            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun datePickerCheckIn(txt : TextView){
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
                dateCheckInSave = date
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun datePickerCheckOut(txt : TextView){
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

                dateCheckOutSave = date
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }


}