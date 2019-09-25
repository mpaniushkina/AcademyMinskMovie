package com.example.academyminskmovie

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val btnTrailer: Button = findViewById(R.id.btnTrailer)
        btnTrailer.setOnClickListener {
            openUrl(url = "https://youtu.be/eKSB0gXl9dw")
        }
    }

    fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
