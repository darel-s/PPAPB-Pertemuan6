package com.example.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.dialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val province = arrayOf(
        "Jawa Tengah",
        "Daerah Istimewa Yogyakarta",
        "Bali",
    )
    private val country = arrayOf(
        "Indonesia",
        "Singapore",
        "Australia"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        with(binding) {
            val adapterProvince = ArrayAdapter (this@MainActivity, android.R.layout.simple_spinner_item, province)
            adapterProvince.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerProvince.adapter = adapterProvince

            val adapterCountry = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, country)
            adapterCountry.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerCountry.adapter = adapterCountry
        }
    }
}