package com.example.countryapp.model

data class Country(
    val name: Name,
    val capital: List<String>?,
    val region: String?,
    val population: Int,
    val flags: Flags
)

data class Name(
    val common: String
)

data class Flags(
    val png: String
)
