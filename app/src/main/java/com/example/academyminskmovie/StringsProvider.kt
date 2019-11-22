package com.example.academyminskmovie

import android.content.Context
import androidx.annotation.StringRes

class StringsProvider(val context: Context) {

    fun getString(@StringRes stringResId: Int) = context.getString(stringResId)

    fun getString(@StringRes stringResId: Int, vararg params: Any) : String {
        return context.getString(stringResId, *params)
    }

}