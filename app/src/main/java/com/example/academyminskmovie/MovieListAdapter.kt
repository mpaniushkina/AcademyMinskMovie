package com.example.academyminskmovie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_row.view.*

class MovieListAdapter(
    context: Context,
    private val movieList: List<FilmList>,
    private val clickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private fun getItem(position: Int): FilmList = movieList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.movie_row, parent, false), clickListener)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View, listener: (position: Int) -> Unit)
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
            Picasso.get().load(moviesList.filmImage).into(filmImage)
            filmTitle.text = moviesList.filmTitle
            filmOverview.text = moviesList.filmOverview
        }
    }
}