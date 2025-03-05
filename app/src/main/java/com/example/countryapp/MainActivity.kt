package com.example.countryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.adapter.CountryAdapter
import com.example.countryapp.model.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val countryList = mutableListOf<Country>()
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CountryAdapter(countryList) { country ->
            val intent = Intent(this, CountryDetailActivity::class.java)
            intent.putExtra("country_name", country.name.common)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        fetchCountries()
    }

    private fun fetchCountries() {
        ApiClient.apiService.getAllCountries().enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful) {
                    countryList.clear()
                    response.body()?.let { countryList.addAll(it) }
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to load countries", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
