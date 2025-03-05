package com.example.countryapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.countryapp.model.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryDetailActivity : AppCompatActivity() {

    private lateinit var countryDetailsText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        countryDetailsText = findViewById(R.id.countryDetailsText)

        val countryName = intent.getStringExtra("country_name")

        countryName?.let { fetchCountryDetails(it) }
    }

    private fun fetchCountryDetails(name: String) {
        ApiClient.apiService.getCountryDetails(name).enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful) {
                    response.body()?.firstOrNull()?.let { country ->
                        countryDetailsText.text = "Name: ${country.name.common}\nCapital: ${country.capital?.joinToString()}\n" +
                                "Region: ${country.region}\nPopulation: ${country.population}"
                    }
                }
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Toast.makeText(this@CountryDetailActivity, "Failed to load country details", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
