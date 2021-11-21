package com.hugo.evaluation.interfaces

import com.hugo.evaluation.model.api.JsonMovie
import com.hugo.evaluation.model.api.JsonTMDB
import com.hugo.evaluation.view.adapter.Movie
import retrofit2.Retrofit

interface InterfacesMovie {
    interface MovieView{
        fun showMovie(movie: MutableList<Movie>?)
        fun getMovie()
        fun showError(msg: String)
    }
    interface MoviePrecenter{
        fun showMovie(movie: MutableList<Movie>?)
        fun getMovie()
        fun showError(msg: String)
    }
    interface MovieInteractor{
        fun getMovies()
    }
    interface MovieModel{
        fun getItemDataBase()
        fun getApi()
        suspend fun getMovies(): JsonTMDB
        fun getCreateInstance(): Retrofit
    }
}