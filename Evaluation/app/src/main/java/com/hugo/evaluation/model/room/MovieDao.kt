package com.hugo.moviesofmine.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieEntity")
    suspend fun getAll(): MutableList<MovieEntity>

    @Insert
    suspend fun insert(movieEntity:MovieEntity): Long

    @Query("DELETE FROM MovieEntity")
    suspend fun delete()


}