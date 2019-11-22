package com.example.academyminskmovie.fragments.filmdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.example.academyminskmovie.*
import com.example.academyminskmovie.data.FilmList
import com.example.academyminskmovie.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : BaseFragment() {

    private lateinit var movie: FilmList
    private lateinit var viewModel: DetailsViewModel

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getParcelable(ARGS_MOVIE)!!
        viewModel = ViewModelProviders.of(
            this,
            DetailsViewModelFactory(
                Dependencies.moviesRepository,
                context!!,
                movie
            )
        ).get(DetailsViewModel::class.java)
    }

    override fun getLayoutResId() = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.openTrailerUrl.observe(
            this,
            Observer { filmTrailer -> openUrl(filmTrailer) }
        )
        viewModel.error.observe(
            this,
            Observer { errorMessage ->
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        )

        arguments?.getParcelable<FilmList>(ARGS_MOVIE)?.run {

            //Picasso.get().load(filmBanner).into(imgBanner)
            imgBanner.load(filmImageUrl)
            val preview = view.findViewById<ImageView>(R.id.imgPreview)
            //Picasso.get().load(filmImage).into(preview)
            preview.load(filmImageUrl)
            view.findViewById<TextView>(R.id.tvFilmTitle).text = filmTitle
            view.findViewById<TextView>(R.id.tvRelData).text = filmReleaseDate
            view.findViewById<TextView>(R.id.tvDescription).text = filmOverview

            val btnTrailer: Button = view.findViewById(R.id.btnTrailer)
            btnTrailer.setOnClickListener {
                viewModel.onTrailerButtonClicked()
            }
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}