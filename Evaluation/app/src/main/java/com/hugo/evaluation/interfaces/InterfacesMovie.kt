package com.hugo.evaluation.interfaces

import com.hugo.evaluation.model.api.JsonMovie
import com.hugo.evaluation.model.api.JsonTMDB
import com.hugo.evaluation.view.adapter.Movie
import retrofit2.Retrofit

interface InterfacesMovie {
    interface MovieView{
        fun showMovie(movie: MutableList<Movie>?)
        fun getMovie()
    }
    interface MoviePrecenter{
        fun showMovie(movie: MutableList<Movie>?)
        fun getMovie()
    }
    interface MovieInteractor{
        fun getApi()
    }
    interface MovieModel{
        fun getApi()
        suspend fun getMovies(): JsonTMDB
        fun getCreateInstance(): Retrofit
    }
}