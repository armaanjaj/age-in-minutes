package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*
import java.text.*

class MainActivity : AppCompatActivity() {

    private var selectedDate: TextView? = null
    private var inMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.datePicker)
        selectedDate = findViewById(R.id.selectedDate)
        inMinutes = findViewById(R.id.minutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)  // date

        val dpdiaglogue = DatePickerDialog(this,

        // removed DatePickerDialog.OnDateSetListener line, because IDE automatically guesses
        { _, year, month, dayOfMonth -> //DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            // months are calculated from 0 -> 11
            var fullDate = "$dayOfMonth/${month+1}/$year"

            selectedDate?.text = fullDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(fullDate)

            // .let is the approach to make sure only execute the code if field is not empty
            theDate?.let {
                val selectedDateInMinutes = theDate.time / 60000 // 6000 -> milliseconds to seconds and seconds to minutes
                val currDate = sdf.parse(sdf.format(System.currentTimeMillis())).time / 60000

                currDate?.let {
                    val differenceInMinutes = currDate - selectedDateInMinutes

                    inMinutes?.text = differenceInMinutes.toString()
                }
            }

        }, year, month, day
        )
        dpdiaglogue.datePicker.maxDate = System.currentTimeMillis()-86400000
        dpdiaglogue.show()
    }
}