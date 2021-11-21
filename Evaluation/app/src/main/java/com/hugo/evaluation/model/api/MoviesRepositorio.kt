package com.hugo.evaluation.model.api

import com.hugo.evaluation.interfaces.InterfacesMovie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.hugo.evaluation.view.adapter.Movie
import com.hugo.moviesofmine.model.MovieApp
import com.hugo.moviesofmine.model.MovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class MoviesRepositorio(var moviePrecenter: InterfacesMovie.MoviePrecenter): InterfacesMovie.MovieModel {
    private var link: String = "https://image.tmdb.org/t/p/w500/"
    override fun getApi() {
        CoroutineScope(Dispatchers.IO).launch {
            MovieApp.database.MovieDao().delete()
            var results = getMovies()
            val mlMovies = mutableListOf<Movie>()
            for (item in results!!.results) {
                mlMovies.add(Movie(item.title,item.original_language,item.popularity,item.vote_average.toString(),item.vote_count.toString(),"${link}${item.backdrop_path}" ))
                MovieApp.database.MovieDao().insert(MovieEntity(Random.nextLong(),item.title,item.original_language,item.popularity,item.vote_average.toString(),item.vote_count.toString(),"${link}${item.backdrop_path}" ))
            }
            moviePrecenter.showMovie(mlMovies)
        }
    }

    override suspend fun getMovies(): JsonTMDB {
        val call = getCreateInstance().create(ApiJMDB::class.java)
            .getMovies("movie/popular?api_key=edcd960b3ba5322cc0702c0c02f8ccba")
        return call.body() as JsonTMDB
    }

    override fun getCreateInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}