package com.example.academyminskmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager

class ViewPager : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.view_pager, container, false)
        val movies = arguments?.getParcelableArrayList<FilmList>(ARGS_MOVIE)
            ?: throw IllegalArgumentException("Missing movie argument")
        val position = arguments?.getInt(ARGS_MOVIE_POSITION) ?: 0

        view.findViewById<ViewPager>(R.id.viewPager).run {
            adapter = ViewPagerAdapter(childFragmentManager, movies)
            currentItem = position
        }

        return view
    }

    companion object {

        private const val ARGS_MOVIE = "ARGS_MOVIE"
        private const val ARGS_MOVIE_POSITION = "ARGS_MOVIE_POSITION"

        fun newInstance(
            movie: List<FilmList>,
            position: Int
        ): com.example.academyminskmovie.ViewPager {
            val fragment = ViewPager()
            val bundle = Bundle()
            bundle.run {
                putParcelableArrayList(ARGS_MOVIE, ArrayList(movie))
                putInt(ARGS_MOVIE_POSITION, position)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}