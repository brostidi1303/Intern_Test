package com.example.currencyconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class LoadingActivity : AppCompatActivity() {

    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        button = findViewById(R.id.btnLoading)
        button.setOnClickListener{ view ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}