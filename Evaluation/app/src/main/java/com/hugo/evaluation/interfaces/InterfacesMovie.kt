package com.hugo.evaluation.interfaces

import com.hugo.evaluation.model.api.JsonTMDB
import com.hugo.evaluation.view.adapter.Movie
import retrofit2.Retrofit
//Se crea una intefaz general que contenca la estructura del MVP
interface InterfacesMovie {
    //Se crea la intefaz que va a contener el modelo para el fragmente de peliculas
    interface MovieView {
        fun showMovie(movie: MutableList<Movie>?)
        fun getMovie()
        fun showError(msg: String)
    }

    //Se crea el presenter que recibira la vista para optener sus valores
    interface MoviePrecenter {
        fun showMovie(movie: MutableList<Movie>?)
        fun getMovie()
        fun showError(msg: String)
    }

    //Se crea el interactor que lidiara con el modelo y donde se realizara la toma de decisiones
    interface MovieInteractor {
        fun getMovies()
    }

    //Se crea el modelo que nos regresara las peliculas
    interface MovieModel {
        fun getItemDataBase()
        fun getApi()
        suspend fun getMovies(): JsonTMDB
        fun getCreateInstance(): Retrofit
    }
}