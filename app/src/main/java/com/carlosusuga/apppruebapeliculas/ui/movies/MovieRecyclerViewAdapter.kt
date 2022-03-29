package com.carlosusuga.apppruebapeliculas.ui.movies

import android.media.Image
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.transform.CircleCropTransformation
import com.carlosusuga.apppruebapeliculas.R
import com.carlosusuga.apppruebapeliculas.common.Constante
import com.carlosusuga.apppruebapeliculas.databinding.FragmentMovieListBinding
import com.carlosusuga.apppruebapeliculas.retrofit.models.Movie

class MovieRecyclerViewAdapter() : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var movies: List<Movie> = ArrayList()

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Movie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMovieListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.tvTittle.text = item.title
        holder.tvCaloficacion.text = item.vote_average.toString()
        holder.imgPelicula.load(Constante.IMAGEN_URL + item.poster_path){
            crossfade(true)
            placeholder(R.drawable.ic_avatar_peliculas)
            transformations(CircleCropTransformation())
        }
    }

    override fun getItemCount(): Int = movies.size
    fun setData(popularMovies: List<Movie>?) {
        movies = popularMovies!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: FragmentMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgPelicula: ImageView = binding.imgAvPeliculas
        val tvTittle: TextView = binding.tvTittle
        val tvCaloficacion: TextView = binding.tvCalificacion

//        override fun toString(): String {
//            return super.toString() + " '" + contentView.text + "'"
//        }
    }

}