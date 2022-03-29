package com.carlosusuga.apppruebapeliculas.retrofit

import com.carlosusuga.apppruebapeliculas.common.Constante
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDBClient {
    private val theMovieDBService: TheMovieDBService
    private val retrofit: Retrofit

    companion object{
        var instance: TheMovieDBClient? = null
        get(){
            if(field == null){
                instance = TheMovieDBClient()
            }
            return field
        }
    }
    init {
        //Incluir el interceptor que hemos definido
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(TheMovieDBInterceptor())

        val client = okHttpClientBuilder.build()

        //Constructor del cliente en retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(Constante.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        // Intanciamos el servicio retrofit apartir del objeto Retrofit
        theMovieDBService = retrofit.create(TheMovieDBService::class.java)
    }

    fun getTheMovieDBService() = theMovieDBService
}