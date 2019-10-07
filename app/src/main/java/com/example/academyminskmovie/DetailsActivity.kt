package com.example.academyminskmovie

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.example.academyminskmovie.FilmList as FilmList

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val movie = intent?.getParcelableExtra<FilmList>(ARGS_FILM)
            ?: throw IllegalArgumentException("Missing film argument")

        val imgBanner = findViewById<ImageView>(R.id.imgBanner)
        Picasso.get().load(movie.filmBanner).into(imgBanner)
        val imgPreview = findViewById<ImageView>(R.id.imgPreview)
        Picasso.get().load(movie.filmImage).into(imgPreview)
        findViewById<TextView>(R.id.tvFilmTitle).text = movie.filmTitle
        findViewById<TextView>(R.id.tvRelData).text = movie.filmRelData
        findViewById<TextView>(R.id.tvDescription).text = movie.filmOverview

        val btnTrailer: Button = findViewById(R.id.btnTrailer)
        btnTrailer.setOnClickListener {
            openUrl(movie.filmTrailer)
        }
    }

    fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    companion object {

        private const val ARGS_FILM = "ARGS_FILM"

        fun createIntent(context: Context, movie: FilmList): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(ARGS_FILM, movie)
            return intent
        }
    }
}

