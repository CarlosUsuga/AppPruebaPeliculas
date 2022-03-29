package com.carlosusuga.apppruebapeliculas.retrofit.models

data class PopularMoviesResponse(
    val page: Int,
    val results: List<Movie>
)