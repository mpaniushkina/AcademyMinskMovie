package com.example.academyminskmovie.fragments.movieslist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.academyminskmovie.adapters.MovieListAdapter
import com.example.academyminskmovie.threads.CoroutinesActivity
import com.example.academyminskmovie.data.FilmList
import com.example.academyminskmovie.Dependencies
import com.example.academyminskmovie.fragments.BaseFragment
import com.example.academyminskmovie.R
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : BaseFragment() {

    private lateinit var adapter: MovieListAdapter
    private lateinit var viewModel: MoviesViewModel

    override fun getLayoutResId() = R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            rvFilmList.apply {
                //val movies = DataFilmList.generateFilmList()
                this@MoviesFragment.adapter =  MovieListAdapter(
                    it,
                    emptyList()
                    //movies
                ) { movies, position ->
                    showDetailsFragment(movies, position)
                }

                adapter = this@MoviesFragment.adapter
                layoutManager = LinearLayoutManager(it)
                addItemDecoration(DividerItemDecoration(it, DividerItemDecoration.VERTICAL))
            }

            viewModel = ViewModelProviders.of(
                this,
                MoviesViewModelFactory(
                    Dependencies.moviesRepository,
                    context!!
                )
            ).get(MoviesViewModel::class.java)

            val progressBar: ProgressBar  = moviesProgressBar
            viewModel.isProgressBarVisible.observe(this, Observer { isVisible ->
               progressBar.isVisible = isVisible
            })

            viewModel.error.observe(this, Observer { errorMessage ->
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            })

            viewModel.movies.observe(this, Observer { movies ->
                adapter.movies = movies
                adapter.notifyDataSetChanged()
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.example.academyminskmovie.R.menu.option_menu, menu)
      super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.coroutinesMenu -> {
                val intent = Intent(activity, CoroutinesActivity::class.java).apply { putExtra("extra_type", 1) }
                startActivity(intent)
                return true
            }
            R.id.threadsMenu -> {
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

    override fun getToolbarId() = R.id.toolbar
}