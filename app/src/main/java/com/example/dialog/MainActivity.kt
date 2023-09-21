package com.example.dialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.dialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding : ActivityMainBinding
    private val province = arrayOf(
        "Jawa Tengah",
        "Daerah Istimewa Yogyakarta",
        "Bali",
    )
    private lateinit var country : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        country = resources.getStringArray(R.array.country)

        with(binding) {
            val adapterProvince = ArrayAdapter (this@MainActivity, android.R.layout.simple_spinner_item, province)
            adapterProvince.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerProvince.adapter = adapterProvince

            val adapterCountry = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, country)
            adapterCountry.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerCountry.adapter = adapterCountry

            spinnerCountry.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                        val countries = country[position]
                        Toast.makeText(this@MainActivity, countries, Toast.LENGTH_SHORT).show()
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }

            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            ) {_,year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
            }

            timePicker.setOnTimeChangedListener{view, hourOfDay, minute ->
                var selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT).show()
            }


            btnShowCalendar.setOnClickListener {
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.show(supportFragmentManager, "datePicker")
            }

            btnShowTimePicker.setOnClickListener {
                val timePicker = TimePicker()
                timePicker.show(supportFragmentManager, "timePicker")
            }

        }
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val selectedDate = "$p3/${p2+1}/$p1"
        Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        val selectedTime = String.format("%02d:%02d", p1, p2)
        Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT).show()
    }

}
