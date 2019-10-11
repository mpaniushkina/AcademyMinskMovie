package com.example.academyminskmovie

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager,
                       private val arrayList: List<FilmList>
                       ): FragmentPagerAdapter(fragmentManager) {

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