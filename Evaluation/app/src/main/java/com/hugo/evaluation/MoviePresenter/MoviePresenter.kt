package com.hugo.evaluation.MoviePresenter

import com.hugo.evaluation.interactor.MovieInteractor
import com.hugo.evaluation.interfaces.InterfacesMovie
import com.hugo.evaluation.view.adapter.Movie

class MoviePresenter(var movieView: InterfacesMovie.MovieView): InterfacesMovie.MoviePrecenter {
    private var movieInteractor: InterfacesMovie.MovieInteractor = MovieInteractor(this)

    override fun showMovie(movie: MutableList<Movie>?) {
        movieView.showMovie(movie)
    }

    override fun getMovie() {
        movieInteractor.getApi()
    }
}