package com.example.academyminskmovie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.academyminskmovie.fragments.BaseFragment
import com.example.academyminskmovie.fragments.movieslist.MoviesFragment
import com.example.academyminskmovie.interfaces.IFragmentListener
import com.example.academyminskmovie.R


class MoviesActivity : AppCompatActivity(),
    IFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        replaceFragment(MoviesFragment())
    }

    override fun replaceFragment(fragment: BaseFragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.containerViewPager, fragment)
            .commit()
    }
}
