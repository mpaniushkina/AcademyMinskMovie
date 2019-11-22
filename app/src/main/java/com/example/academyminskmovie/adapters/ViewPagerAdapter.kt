package com.example.academyminskmovie.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.academyminskmovie.fragments.filmdetails.DetailsFragment
import com.example.academyminskmovie.data.FilmList

class ViewPagerAdapter(fragmentManager: FragmentManager,
                       private val arrayList: List<FilmList>
                       ): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        val movie = when {
            arrayList.isNotEmpty() && position <= count - 1 -> arrayList[position]
            else -> null
        }
        return movie?.run { DetailsFragment.newInstance(this) }!!
    }

    override fun getCount(): Int {
        return arrayList.size
    }
}