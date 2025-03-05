package com.example.countryapp

import com.example.countryapp.model.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("v3.1/all")
    fun getAllCountries(): Call<List<Country>>

    @GET("v3.1/name/{name}")
    fun getCountryDetails(@Path("name") name: String): Call<List<Country>>
}
