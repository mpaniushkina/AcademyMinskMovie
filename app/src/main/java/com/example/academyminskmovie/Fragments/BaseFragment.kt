package com.example.academyminskmovie.Fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.academyminskmovie.Interfaces.IFragmentListener
import java.lang.Exception

abstract class BaseFragment : Fragment(),
    IFragmentListener {

    private lateinit var fragmentListener: IFragmentListener

    @LayoutRes
    abstract fun getLayoutResId(): Int

    @IdRes
    open fun getToolbarId(): Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(getLayoutResId(), container, false)

        if (getToolbarId() != 0) {
            (activity as AppCompatActivity)?.let {
                it.setSupportActionBar(root.findViewById(getToolbarId()) as Toolbar)
            }
        }

        return root
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