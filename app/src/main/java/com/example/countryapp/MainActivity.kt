package com.example.countryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.adapter.CountryAdapter
import com.example.countryapp.model.Country
import com.example.countryapp.repository.CountryRepository
import com.example.countryapp.viewmodel.CountryViewModel
import com.example.countryapp.viewmodel.CountryViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountryAdapter

    private val viewModel: CountryViewModel by viewModels {
        CountryViewModelFactory(CountryRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CountryAdapter(emptyList()) { country ->
            val intent = Intent(this, CountryDetailActivity::class.java)
            intent.putExtra("country_name", country.name.common)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        viewModel.countries.observe(this) { countries ->
            adapter.updateData(countries)
        }

        viewModel.fetchCountries()
    }
}
