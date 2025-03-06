package com.example.countryapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.countryapp.repository.CountryRepository
import com.example.countryapp.viewmodel.CountryViewModel
import com.example.countryapp.viewmodel.CountryViewModelFactory

class CountryDetailActivity : AppCompatActivity() {

    private lateinit var countryDetailsText: TextView

    private val viewModel: CountryViewModel by viewModels {
        CountryViewModelFactory(CountryRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        countryDetailsText = findViewById(R.id.countryDetailsText)

        val countryName = intent.getStringExtra("country_name")

        viewModel.selectedCountry.observe(this) { country ->
            country?.let {
                countryDetailsText.text = "Name: ${it.name.common}\nCapital: ${it.capital?.joinToString()}\n" +
                        "Region: ${it.region}\nPopulation: ${it.population}"
            }
        }

        countryName?.let { viewModel.fetchCountryDetails(it) }
    }
}
