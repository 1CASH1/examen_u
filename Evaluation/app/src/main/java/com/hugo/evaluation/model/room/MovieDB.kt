package com.hugo.evaluation.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hugo.moviesofmine.model.MovieDao
import com.hugo.moviesofmine.model.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)

abstract class MovieDB : RoomDatabase() {
    abstract fun MovieDao(): MovieDao
}