package com.example.countries.api

import android.content.Context
import com.example.countries.utils.Constants.Companion.BASE_URL
import io.requestly.rqinterceptor.api.RQCollector
import io.requestly.rqinterceptor.api.RQInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance{

    fun getInstance(context: Context) : CountriesApi{

        val collector = RQCollector(context=context, sdkKey="8Krw0Jypwovy79fl48VC")

        val rqInterceptor = RQInterceptor.Builder(context)
            .collector(collector)
            .build()

        val client = OkHttpClient.Builder()
            .addInterceptor(rqInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }
}
