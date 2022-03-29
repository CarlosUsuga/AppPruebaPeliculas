package com.carlosusuga.apppruebapeliculas.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.carlosusuga.apppruebapeliculas.common.MyApp
import com.carlosusuga.apppruebapeliculas.retrofit.TheMovieDBClient
import com.carlosusuga.apppruebapeliculas.retrofit.TheMovieDBService
import com.carlosusuga.apppruebapeliculas.retrofit.models.Movie
import com.carlosusuga.apppruebapeliculas.retrofit.models.PopularMoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TheMovieDBRepository {
    var theMovieDBService: TheMovieDBService? = null
    var theMovieDBClient: TheMovieDBClient? = null
    var popularMovies: MutableLiveData<List<Movie>>? = null

    init {
        theMovieDBClient = TheMovieDBClient.instance
        theMovieDBService = theMovieDBClient?.getTheMovieDBService()
        popularMovies = popularMovies()
    }

    fun popularMovies(): MutableLiveData<List<Movie>>? {
        if(popularMovies == null){
            popularMovies = MutableLiveData<List<Movie>>()
        }

        val call: Call<PopularMoviesResponse>? = theMovieDBService?.getPopularMovies()
        call?.enqueue(object: Callback<PopularMoviesResponse> {
            override fun onResponse(
                call: Call<PopularMoviesResponse>,
                response: Response<PopularMoviesResponse>
            ) {
                if (response.isSuccessful){
                    popularMovies?.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error de conexión", Toast.LENGTH_LONG).show()
            }

        })

        return popularMovies
    }
}