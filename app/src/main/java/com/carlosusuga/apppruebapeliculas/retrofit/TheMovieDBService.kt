package com.carlosusuga.apppruebapeliculas.retrofit

import com.carlosusuga.apppruebapeliculas.retrofit.models.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface TheMovieDBService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<PopularMoviesResponse>
}