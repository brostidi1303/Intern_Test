package com.example.currencyconverter.main

import android.util.Log
import com.example.currencyconverter.data.CurrencyAPI
import com.example.currencyconverter.data.models.CurrencyResponse
import com.example.currencyconverter.util.Resource
import okio.IOException
import javax.inject.Inject

class DMRepo @Inject constructor(
    private val api: CurrencyAPI
) : MainRepo {

    // API call to fetch exchange rates
    override suspend fun getRates(): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates()
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result) // Return data
            } else {
                Resource.Error(response.message()) // Handle error
            }
        } catch (e: IOException) {
            // Lỗi khi mất kết nối mạng
            Resource.Error("No network connection !") // Handle network
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Have an error") // Handle error
        }
    }
}
