package com.carlosusuga.apppruebapeliculas.retrofit

import com.carlosusuga.apppruebapeliculas.common.Constante
import okhttp3.Interceptor
import okhttp3.Response

class TheMovieDBInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val urlWithParams = chain.request().url
            .newBuilder().addQueryParameter(Constante.URL_PARAMS_API_KEY, Constante.API_KEY)
            .addQueryParameter(Constante.URL_PARAMS_LANGUAGE, Constante.URL_PARAMS_IDIOMA)
            .build()

        var request = chain.request()
        request = request?.newBuilder().url(urlWithParams)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()

        return chain.proceed(request)

    }
}