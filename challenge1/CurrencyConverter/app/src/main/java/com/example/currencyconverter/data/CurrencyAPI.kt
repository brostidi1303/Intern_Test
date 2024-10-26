package com.example.currencyconverter.data

import com.example.currencyconverter.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyAPI {
    // API GET request to /latest endpoint to fetch the latest exchange rates
    @GET("/latest?access_key=3af9a14f2d353b5519975fa98cc6a981&base=EUR")
    suspend fun getRates(): Response<CurrencyResponse>
}

