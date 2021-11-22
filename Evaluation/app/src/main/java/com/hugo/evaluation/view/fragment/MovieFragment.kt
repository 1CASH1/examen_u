package com.hugo.evaluation.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hugo.evaluation.R
import com.hugo.evaluation.databinding.FragmentMovieBinding
import com.hugo.evaluation.helper.CheckNetwork
import com.hugo.evaluation.interfaces.InterfacesMovie
import com.hugo.evaluation.presenter.MoviePresenter
import com.hugo.evaluation.view.adapter.AdapterMovies
import com.hugo.evaluation.view.adapter.Movie
import javax.inject.Singleton

class MovieFragment : Fragment(R.layout.fragment_movie), InterfacesMovie.MovieView {
    @Singleton
    private lateinit var mBinding: FragmentMovieBinding
    @Singleton
    private var moviePresenter: InterfacesMovie.MoviePrecenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentMovieBinding.bind(view)
        //inicializar el presenter y solcitar las peliculas
        moviePresenter = MoviePresenter(this, CheckNetwork(requireActivity()).isOnline())
        getMovie()
    }

    //mistrar las peliculas solcitadas
    override fun showMovie(movie: MutableList<Movie>?) {
        activity?.runOnUiThread {
            try {
                if ((movie?.size ?: 0) > 0) {
                    mBinding.lvMovie.adapter =
                        AdapterMovies(this.context, R.layout.item_movie, movie)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    //opteners las pelculas
    override fun getMovie() {
        moviePresenter?.getMovie()
    }

    //mostrar errores
    override fun showError(msg: String) {
        Toast.makeText(this.context, msg, Toast.LENGTH_LONG).show()
    }
}