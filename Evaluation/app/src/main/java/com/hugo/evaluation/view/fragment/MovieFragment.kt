package com.hugo.evaluation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.hugo.evaluation.MoviePresenter.MoviePresenter
import com.hugo.evaluation.R
import com.hugo.evaluation.databinding.FragmentMapBinding
import com.hugo.evaluation.databinding.FragmentMovieBinding
import com.hugo.evaluation.interfaces.InterfacesMovie
import com.hugo.evaluation.view.adapter.AdapterMovies
import com.hugo.evaluation.view.adapter.Movie
import java.nio.channels.MulticastChannel

class MovieFragment : Fragment(R.layout.fragment_movie), InterfacesMovie.MovieView {
    private lateinit var mBinding: FragmentMovieBinding
    private var moviePresenter: InterfacesMovie.MoviePrecenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentMovieBinding.bind(view)

        moviePresenter= MoviePresenter(this)
        getMovie()
//        var mlMovie: MutableList<Movie> = mutableListOf()
//        mlMovie.add(Movie("eee","eee","eee","eee","eee","https://image.tmdb.org/t/p/w500/5hNcsnMkwU2LknLoru73c76el3z.jpg"))
//        mlMovie.add(Movie("eee","eee","eee","eee","eee","https://image.tmdb.org/t/p/w500/5hNcsnMkwU2LknLoru73c76el3z.jpg"))
//        mlMovie.add(Movie("eee","eee","eee","eee","eee","https://image.tmdb.org/t/p/w500/5hNcsnMkwU2LknLoru73c76el3z.jpg"))
//        mBinding.lvMovie.adapter = AdapterMovies(this.context, R.layout.item_movie, mlMovie)

    }

    override fun showMovie(movie: MutableList<Movie>?) {
        activity?.runOnUiThread{
            try {
                if ((movie?.size ?: 0) >0) {
                    mBinding.lvMovie.adapter = AdapterMovies(this.context, R.layout.item_movie, movie)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    override fun getMovie() {
        moviePresenter?.getMovie()
    }
}