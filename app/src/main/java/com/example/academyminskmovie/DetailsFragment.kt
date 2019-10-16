package com.example.academyminskmovie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : BaseFragment() {

    companion object {

        public const val ARGS_MOVIE = "ARGS_MOVIE"

        fun newInstance(movie: FilmList): DetailsFragment {
            val fragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARGS_MOVIE, movie)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutResId() = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<FilmList>(ARGS_MOVIE)?.run {

            Picasso.get().load(filmBanner).into(imgBanner)

            val preview = view.findViewById<ImageView>(R.id.imgPreview)
            Picasso.get().load(filmImage).into(preview)
            view.findViewById<TextView>(R.id.tvFilmTitle).text = filmTitle
            view.findViewById<TextView>(R.id.tvRelData).text = filmRelData
            view.findViewById<TextView>(R.id.tvDescription).text = filmOverview

            val btnTrailer: Button = view.findViewById(R.id.btnTrailer)
            btnTrailer.setOnClickListener {
                openUrl(filmTrailer)
            }
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}