package com.example.academyminskmovie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmList (
    val filmBanner: String,
    val filmImage: String,
    val filmTitle: String,
    val filmOverview: String,
    val filmRelData: String,
    val filmTrailer: String
) : Parcelable

