package com.example.academyminskmovie.fragments.movieslist

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.academyminskmovie.adapters.ViewPagerAdapter
import com.example.academyminskmovie.data.FilmList
import com.example.academyminskmovie.fragments.BaseFragment
import com.example.academyminskmovie.fragments.filmdetails.DetailsFragment.Companion.ARGS_MOVIE
import com.example.academyminskmovie.R
import java.util.ArrayList

class ViewPagerFragment : BaseFragment() {

    override fun getLayoutResId() = R.layout.view_pager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movies = arguments?.getParcelableArrayList<FilmList>(ARGS_MOVIE)
            ?: throw IllegalArgumentException("Missing movie argument")
        val position = arguments?.getInt(ARGS_MOVIE_POSITION) ?: 0

        view.findViewById<ViewPager>(R.id.viewPager).run {
            adapter = ViewPagerAdapter(
                childFragmentManager,
                movies
            )
            currentItem = position
        }
    }

    companion object {

        private const val ARGS_MOVIE_POSITION = "ARGS_MOVIE_POSITION"

        fun newInstance(
            movie: List<FilmList>,
            position: Int
        ): ViewPagerFragment {
            val fragment = ViewPagerFragment()
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
