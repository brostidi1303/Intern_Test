package com.example.currencyconverter.di

import android.app.Application
import com.example.currencyconverter.data.CurrencyAPI
import com.example.currencyconverter.main.DMRepo
import com.example.currencyconverter.main.MainRepo
import com.example.currencyconverter.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
// Base URL for the API
private const val BASE_URL = "https://api.exchangeratesapi.io/v1/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Provides a singleton instance of CurrencyAPI for making API calls
    @Singleton
    @Provides
    fun provideCurrencyApi(): CurrencyAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyAPI::class.java)

    // Provides MainRepo with DMRepo implementation for accessing data
    @Singleton
    @Provides
    fun provideMainRepository(api: CurrencyAPI): MainRepo = DMRepo(api)

    // Provides DispatcherProvider for coroutine thread handling
    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}
