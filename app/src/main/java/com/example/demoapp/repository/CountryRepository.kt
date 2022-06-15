package com.example.demoapp.repository

import com.example.countries.api.ApiHelper

class CountryRepository(private val apiHelper: ApiHelper) {

    suspend fun getAsianCountries() = apiHelper.getAsian()
    suspend fun getAmericanCountries() = apiHelper.getAmerican()
    suspend fun getEuropeanCountries() = apiHelper.getEuropean()
    suspend fun getAfricanCountries() = apiHelper.getAfrican()
}