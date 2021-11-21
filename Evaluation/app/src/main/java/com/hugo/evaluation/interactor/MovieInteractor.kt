package com.hugo.evaluation.interactor

import com.hugo.evaluation.interfaces.InterfacesMovie
import com.hugo.evaluation.model.api.MoviesRepositorio

class MovieInteractor(var moviePrecenter: InterfacesMovie.MoviePrecenter): InterfacesMovie.MovieInteractor {
    var moviesRepositorio: InterfacesMovie.MovieModel = MoviesRepositorio(moviePrecenter)
    override fun getApi() {

        moviesRepositorio.getApi()
    }

    fun isInternetAvailable(): Boolean {
        return internet.isAvailable()
    }

}