package com.example.academyminskmovie.Threads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.academyminskmovie.Fragments.BaseFragment
import com.example.academyminskmovie.R

class CoroutinesActivity : AppCompatActivity() {

    private var taskFragment: TaskFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        TaskFragment().also {
            it.arguments = intent.extras
            taskFragment = it
            replaceFragment(it)
        }

    }

    fun replaceFragment(fragment: BaseFragment) {
        supportFragmentManager
            .beginTransaction()
            //.addToBackStack(null)
            .add(R.id.coroutinesTask, fragment)
            .commit()
    }
}