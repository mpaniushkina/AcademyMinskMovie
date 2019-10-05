package com.example.academyminskmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val movies = DataFilmList.generateFilmList()
        val adapter = MovieListAdapter(this, movies) { position ->
            val movie = movies[position]
            val intent = DetailsActivity.createIntent(this, movie)
            startActivity(intent)
        }

        val movieList = findViewById<RecyclerView>(R.id.rvFilmList)
        movieList.adapter = adapter
        movieList.layoutManager = LinearLayoutManager(this)

        movieList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}
