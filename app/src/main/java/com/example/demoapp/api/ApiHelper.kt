package com.example.countries.api

class ApiHelper(private val api: CountriesApi) {
    suspend fun getAsian() = api.getAsianCountries()
    suspend fun getAfrican() = api.getAfricanCountries()
    suspend fun getAmerican() = api.getAmericanCountries()
    suspend fun getEuropean() = api.getEuropeanCountries()
}