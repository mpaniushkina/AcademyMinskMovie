package com.example.academyminskmovie

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment: BaseFragment() {

    override fun getLayoutResId() = R.layout.fragment_movies

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            rvFilmList.apply {
                val movies = DataFilmList.generateFilmList()
                adapter =  MovieListAdapter(it, movies) { position ->
                    showDetailsFragment(movies, position)
                }
                layoutManager = LinearLayoutManager(it)
                addItemDecoration(DividerItemDecoration(it, DividerItemDecoration.VERTICAL))
            }
        }
    }

    private fun showDetailsFragment(movies: List<FilmList>, position: Int) {
        replaceFragment(ViewPagerFragment.newInstance(movies, position))
    }
}