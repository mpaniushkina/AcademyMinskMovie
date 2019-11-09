package com.example.academyminskmovie.Threads

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class TaskViewModelFactory (private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == TaskViewModel::class.java) {
            @Suppress("UNCHECKED_CAST")
            TaskViewModel(StringsProvider(context)) as T
        } else {
            throw IllegalArgumentException("Unknown view model class $modelClass")
        }
    }
}