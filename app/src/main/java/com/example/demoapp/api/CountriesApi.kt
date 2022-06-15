package com.example.countries.api

import com.example.demoapp.mod.Country
import retrofit2.http.GET

interface CountriesApi {

    @GET("v3.1/region/asia")
    suspend fun getAsianCountries() : ArrayList<Country>

    @GET("v3.1/region/europe")
    suspend fun getEuropeanCountries() : ArrayList<Country>

    @GET("v3.1/region/america")
    suspend fun getAmericanCountries() : ArrayList<Country>

    @GET("v3.1/region/africa")
    suspend fun getAfricanCountries() : ArrayList<Country>
}