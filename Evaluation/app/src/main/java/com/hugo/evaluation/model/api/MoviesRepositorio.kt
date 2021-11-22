package com.hugo.evaluation.model.api

import com.hugo.evaluation.interfaces.InterfacesMovie
import com.hugo.evaluation.view.adapter.Movie
import com.hugo.moviesofmine.model.MovieApp
import com.hugo.moviesofmine.model.MovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

//Solictu a una api para que obtenga las peliculas que vamos a consultar
class MoviesRepositorio(var moviePrecenter: InterfacesMovie.MoviePrecenter) :
    InterfacesMovie.MovieModel {
    //Link faltante de la respues de la imagen
    private var link: String = "https://image.tmdb.org/t/p/w500/"

    //se optiene la informacion de la base de datos
    override fun getItemDataBase() {
        try {
            //se realiza la corrutina para no saturar el hilo principal
            CoroutineScope(Dispatchers.IO).launch {
                //Se optienen los valores a la base de datos
                var db = MovieApp.database.MovieDao().getAll()
                ///lista para almacenar los rsultados
                val mlMovies = mutableListOf<Movie>()
                //se recorre el bucle
                for (item in db) {
                    //Se insertan los datos correspondientes para la vista
                    mlMovies.add(
                        Movie(
                            item.title,
                            item.original_language,
                            item.popularity,
                            item.vote_average,
                            item.vote_count,
                            "${item.image}"
                        )
                    )
                }
                //se regresa la vista al fracmento
                moviePrecenter.showMovie(mlMovies)

            }
        } catch (e: Exception) {
            //en caso de existir un error se notififca
            moviePrecenter.showError(e.toString())
        }
    }

    //metodo para realizar la peticion a una api
    override fun getApi() {
        try {
            //Se crea una corrutina para ecitar trabajar en el metodo principal
            CoroutineScope(Dispatchers.IO).launch {
                //se limpia la base de datos
                MovieApp.database.MovieDao().delete()
                //se solicita el rsultado del retrofil
                var results = getMovies()
                //se crea una lista q contendra los resultados
                val mlMovies = mutableListOf<Movie>()
                //se recorren los resultados
                for (item in results.results) {
                    //se insertan los resultados para la vista
                    mlMovies.add(
                        Movie(
                            item.title,
                            item.original_language,
                            item.popularity,
                            item.vote_average.toString(),
                            item.vote_count.toString(),
                            "${link}${item.backdrop_path}"
                        )
                    )
                    //se insertan los resultados para la base de datos
                    MovieApp.database.MovieDao().insert(
                        MovieEntity(
                            Random.nextLong(),
                            item.title,
                            item.original_language,
                            item.popularity,
                            item.vote_average.toString(),
                            item.vote_count.toString(),
                            "${link}${item.backdrop_path}"
                        )
                    )
                }
                //Se regresan los resultados para mostrarlos en la vista
                moviePrecenter.showMovie(mlMovies)
            }
        } catch (e: Exception) {
            //se determina un error en caso de que este exista
            moviePrecenter.showError(e.toString())
        }
    }

    //se crea manda a llamar el retrifil para que devuelva el json correspondiente
    //Se determina con suspend para que no corra en el hilo principal
    override suspend fun getMovies(): JsonTMDB {
        //se accinan los atributos correspontientes al retrofil para realizar la consulta
        val call = getCreateInstance().create(ApiJMDB::class.java)
            .getMovies("movie/popular?api_key=edcd960b3ba5322cc0702c0c02f8ccba")
        return call.body() as JsonTMDB
    }

    //se crea la instacia del retrofil
    override fun getCreateInstance(): Retrofit {
        //se retirna el bjeto retrofil
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}