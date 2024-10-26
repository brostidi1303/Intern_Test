package com.example.currencyconverter.main

import com.example.currencyconverter.data.CurrencyAPI
import com.example.currencyconverter.data.models.CurrencyResponse
import com.example.currencyconverter.util.Resource

interface MainRepo {
    // Method to get exchange rates from the API
    suspend fun getRates(): Resource<CurrencyResponse>
}
