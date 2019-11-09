package com.example.academyminskmovie.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.academyminskmovie.Adapters.MovieListAdapter
import com.example.academyminskmovie.Threads.CoroutinesActivity
import com.example.academyminskmovie.Data.DataFilmList
import com.example.academyminskmovie.Data.FilmList
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : BaseFragment() {

    override fun getLayoutResId() = com.example.academyminskmovie.R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            rvFilmList.apply {
                val movies = DataFilmList.generateFilmList()
                adapter = MovieListAdapter(
                    it,
                    movies
                ) { position ->
                    showDetailsFragment(movies, position)
                }
                layoutManager = LinearLayoutManager(it)
                addItemDecoration(DividerItemDecoration(it, DividerItemDecoration.VERTICAL))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.example.academyminskmovie.R.menu.option_menu, menu)
      super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            com.example.academyminskmovie.R.id.coroutinesMenu -> {
                val intent = Intent(activity, CoroutinesActivity::class.java).apply { putExtra("extra_type", 1) }
                startActivity(intent)
                return true
            }
            com.example.academyminskmovie.R.id.threadsMenu -> {
                val intent = Intent(activity, CoroutinesActivity::class.java).apply { putExtra("extra_type", 2) }
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDetailsFragment(movies: List<FilmList>, position: Int) {
        replaceFragment(
            ViewPagerFragment.newInstance(
                movies,
                position
            )
        )
    }

    override fun getToolbarId() = com.example.academyminskmovie.R.id.toolbar
}