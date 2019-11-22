package com.example.academyminskmovie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.academyminskmovie.data.FilmList
import com.example.academyminskmovie.R

class MovieListAdapter(
    context: Context,
    var movies: List<FilmList>,
    private val clickListener: ( movies: List<FilmList>, position: Int) -> Unit
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private fun getItem(position: Int): FilmList = movies[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            inflater.inflate(
                R.layout.movie_row,
                parent,
                false
            )//, clickListener
        ) { position -> clickListener(movies, position) }
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View, listener: ( position: Int) -> Unit)
        : RecyclerView.ViewHolder(itemView) {

        private val filmImage: ImageView = itemView.findViewById(R.id.filmImage)
        private val filmTitle: TextView = itemView.findViewById(R.id.tvListFilmTitle)
        private val filmOverview: TextView = itemView.findViewById(R.id.tvListFilmOverview)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener(position)
                }
            }
        }

        fun bind(moviesList: FilmList) {
            filmImage.load(moviesList.filmImageUrl)
            //Picasso.get().load(moviesList.filmImage).into(filmImage)
            filmTitle.text = moviesList.filmTitle
            filmOverview.text = moviesList.filmOverview
        }
    }
}