package com.hugo.evaluation.presenter

import com.hugo.evaluation.interactor.MovieInteractor
import com.hugo.evaluation.interfaces.InterfacesMovie
import com.hugo.evaluation.view.adapter.Movie
//presentar para madnar a llamar a los metodos entre la vistas y el interactor siendo este el intermediario
class MoviePresenter(var movieView: InterfacesMovie.MovieView, internet: Boolean) :
    InterfacesMovie.MoviePrecenter {
    private var movieInteractor: InterfacesMovie.MovieInteractor = MovieInteractor(this, internet)

    //metodo para mostrar peliculas
    override fun showMovie(movie: MutableList<Movie>?) {
        //se madan a mostrar las peliculas a la vista
        movieView.showMovie(movie)
    }

    //metodo para obtener las peliculas
    override fun getMovie() {
        //se solcitan las peliculas al interactor
        movieInteractor.getMovies()
    }

    //metodo para mostrar los errores
    override fun showError(msg: String) {
        //se manda a mostrar los errors en la vista
        movieView.showError(msg)
    }
}