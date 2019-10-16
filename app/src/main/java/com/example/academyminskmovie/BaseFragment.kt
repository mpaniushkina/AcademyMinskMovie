package com.example.academyminskmovie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import java.lang.Exception

abstract class BaseFragment : Fragment(), IFragmentListener {

    private lateinit var fragmentListener: IFragmentListener

    @LayoutRes
    abstract fun getLayoutResId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            fragmentListener = context as IFragmentListener
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun replaceFragment(fragment: BaseFragment) {
        fragmentListener.replaceFragment(fragment)
    }

}