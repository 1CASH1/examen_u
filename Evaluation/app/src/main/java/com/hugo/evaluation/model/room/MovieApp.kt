package com.hugo.moviesofmine.model

import android.app.Application
import androidx.room.Room
import com.hugo.evaluation.model.room.MovieDB
import javax.inject.Singleton

@Singleton
class MovieApp : Application() {
    companion object {
        lateinit var database: MovieDB
    }


    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            MovieDB::class.java,
            "movie"
        )
            .build()
    }
}