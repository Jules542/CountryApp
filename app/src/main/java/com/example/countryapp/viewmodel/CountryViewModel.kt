package com.example.countryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countryapp.model.Country
import com.example.countryapp.repository.CountryRepository
import kotlinx.coroutines.launch

class CountryViewModel(private val repository: CountryRepository) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    private val _selectedCountry = MutableLiveData<Country>()
    val selectedCountry: LiveData<Country> get() = _selectedCountry

    fun fetchCountries() {
        viewModelScope.launch {
            val response = repository.getAllCountries()
            if (response.isSuccessful) {
                _countries.postValue(response.body())
            }
        }
    }

    fun fetchCountryDetails(name: String) {
        viewModelScope.launch {
            val response = repository.getCountryDetails(name)
            if (response.isSuccessful) {
                response.body()?.firstOrNull()?.let {
                    _selectedCountry.postValue(it)
                }
            }
        }
    }
}
