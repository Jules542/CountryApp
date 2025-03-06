package com.example.countryapp.repository

import com.example.countryapp.ApiClient
import com.example.countryapp.model.Country
import retrofit2.Response

class CountryRepository {
    suspend fun getAllCountries(): Response<List<Country>> {
        return ApiClient.apiService.getAllCountries()
    }

    suspend fun getCountryDetails(name: String): Response<List<Country>> {
        return ApiClient.apiService.getCountryDetails(name)
    }
}
