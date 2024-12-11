package com.example.voovie.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.voovie.data.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface VoovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)
    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<Movie>>
    @Query("SELECT * FROM movies WHERE id =:movieId")
    suspend fun getMovieById(movieId:Int):Movie?
    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()
}