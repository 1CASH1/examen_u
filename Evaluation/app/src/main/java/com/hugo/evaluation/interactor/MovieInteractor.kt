package com.hugo.evaluation.interactor

import com.hugo.evaluation.interfaces.InterfacesMovie
import com.hugo.evaluation.model.api.MoviesRepositorio
import javax.inject.Singleton

//Interactor para traer la informacion de los moldelos para las peliculas
class MovieInteractor(var moviePrecenter: InterfacesMovie.MoviePrecenter, var Internet: Boolean) :
    InterfacesMovie.MovieInteractor {
    //Variables globales para traer la informacion de los repositorios
    @Singleton
    private var moviesRepositorio: InterfacesMovie.MovieModel = MoviesRepositorio(moviePrecenter)
    //Optener las pliculas
    override fun getMovies() {
        //en base al hecho de si se cuenta con internet accedera en la api en caso cntrario se accede a la api
        if (Internet) {
            moviesRepositorio.getApi()
        } else {
            moviesRepositorio.getItemDataBase()
        }
    }


}