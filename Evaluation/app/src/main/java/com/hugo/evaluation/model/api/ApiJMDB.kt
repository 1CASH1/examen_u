package com.hugo.evaluation.model.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
//Interfas de retrofil a la cual le pasaremos el url dinamico para obtener el objeto de tipo json que se converitra en una estrcutura para que podamos manejarla
interface ApiJMDB {
    @GET
    suspend fun getMovies(@Url url: String): Response<JsonTMDB>
}