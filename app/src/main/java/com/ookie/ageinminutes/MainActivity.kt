package com.ookie.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)

        tvSelectedDate = findViewById(R.id.tvSelectedDateInMinutes)
        tvAgeInMinutes = findViewById(R.id.tvAgeinMinutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }

    }

    fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        var datePickerDialogue = DatePickerDialog(this, {
            //Lambda function
            _, selectedYear, selectedMonth, selectedDayOfTheMonth ->

            //Month starts at 0? so Plus 1
            Toast.makeText(this, "Year was $selectedYear, month was ${selectedMonth + 1}, and the day was $selectedDayOfTheMonth", Toast.LENGTH_SHORT).show()

            val selectedDate = "$selectedDayOfTheMonth/${selectedMonth + 1}/$selectedYear"
            tvSelectedDate?.text = selectedDate

            var simpleDateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)

            val theDate = simpleDateFormat.parse(selectedDate)

            theDate?.let {
                val selectedDateInMinutes = theDate.time/60000

                val currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))

                currentDate?.let {

                    val currentDateinMinutes = currentDate.time/60000

                    val difference = currentDateinMinutes - selectedDateInMinutes

                    tvAgeInMinutes?.text = difference.toString()
                }
            }

        }, year, month, day)

        datePickerDialogue.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePickerDialogue.show()

    }


}