package com.example.countryapp

import com.example.countryapp.model.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("v3.1/all")
    suspend fun getAllCountries(): Response<List<Country>>

    @GET("v3.1/name/{name}")
    suspend fun getCountryDetails(@Path("name") name: String): Response<List<Country>>
}
