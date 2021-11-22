package com.hugo.evaluation.presenter

import com.hugo.evaluation.interactor.MovieInteractor
import com.hugo.evaluation.interfaces.InterfacesMovie
import com.hugo.evaluation.view.adapter.Movie

class MoviePresenter(var movieView: InterfacesMovie.MovieView, internet:Boolean): InterfacesMovie.MoviePrecenter {
    private var movieInteractor: InterfacesMovie.MovieInteractor = MovieInteractor(this,internet)

    override fun showMovie(movie: MutableList<Movie>?) {
        movieView.showMovie(movie)
    }

    override fun getMovie() {
        movieInteractor.getMovies()
    }

    override fun showError(msg: String) {
        movieView.showError(msg)
    }
}