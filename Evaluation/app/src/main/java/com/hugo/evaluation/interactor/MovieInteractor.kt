package com.hugo.evaluation.interactor

import com.hugo.evaluation.interfaces.InterfacesMovie
import com.hugo.evaluation.model.api.MoviesRepositorio

class MovieInteractor(var moviePrecenter: InterfacesMovie.MoviePrecenter,var  Internet: Boolean): InterfacesMovie.MovieInteractor {
    var moviesRepositorio: InterfacesMovie.MovieModel = MoviesRepositorio(moviePrecenter)
    override fun getMovies() {

        if(Internet){
            moviesRepositorio.getApi()
        }else{
            moviesRepositorio.getItemDataBase()
        }
    }


}