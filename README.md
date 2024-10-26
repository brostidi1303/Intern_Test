# Currency-Converter
Currency exchange app displays real-time currency rates. This is an Android app developed in Kotlin using Android Studio, focusing on creating a complete user experience with a modern and intuitive GUI, providing a currency converter with default currency EUR to convert more than 150 currencies implemented in MVVM Architecture.

## Features:
- Display real-time currency rates.
- Use the API generated directly from https://exchangeratesapi.io/ (Due to API limitations, only EUR to other currencies can be converted.)
- Provide a currency converter to convert over 150 currencies.
- User-friendly Interface: Experience smooth and intuitive user interface, ensuring ease of use.

## Library and Technology
//Note

To make sure there are no problems, edit a few places in build.gradle.kts, the plugins and kapt I added below.

plugins {

    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id ("kotlin-android")
    id ("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    
}

kapt {

    correctErrorTypes = true  
    
}

//Retrofit
- implementation("com.squareup.retrofit2:retrofit:2.9.0")
- implementation("com.squareup.retrofit2:converter-gson:2.9.0")

//Dagger
- implementation("com.google.dagger:hilt-android:2.48")
- kapt("com.google.dagger:hilt-android-compiler:2.48")
- kapt("androidx.hilt:hilt-compiler:1.2.0")

//Activity for KTX
- implementation("androidx.activity:activity-ktx:1.9.3")

//Architectural
- implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")

//Lifecycle
- implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
- implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")

//Coroutines
- implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
- implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
  
//Coroutines Scope
- implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")

//Lottie
- implementation ("com.airbnb.android:lottie:6.3.0")

## Link screenshots and videos
https://drive.google.com/drive/folders/1lw0oVXHXMgIujN-qeNp7I9Xj0REzan5Z?usp=sharing
