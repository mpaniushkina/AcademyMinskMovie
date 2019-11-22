package com.example.academyminskmovie.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmList (
    val id: Int,
    val filmTitle: String,
    val filmImageUrl: String,
    val filmOverview: String,
    val filmReleaseDate: String,
    val backdropUrl: String
//    val filmBanner: String,
//    val filmImage: String,
//    val filmTitle: String,
//    val filmOverview: String,
//    val filmRelData: String,
//    val filmTrailer: String
) : Parcelable

