package com.example.academyminskmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MoviesActivity : AppCompatActivity(), IFragmentListener {

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
