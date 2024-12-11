package com.example.voovie.data.repository

import com.example.voovie.data.local.daos.VoovieDao
import com.example.voovie.data.model.Movie
import com.example.voovie.data.model.Result
import com.example.voovie.data.network.VoovieService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
/**
 * Acts as an intermediary between VoovieViewModel and data sources
 * (Room database and NETWORK REQUEST)
 * **/
class VoovieRepository @Inject constructor/**provides means for hilt to to provide instances of
 vovieSearvice*/(
    private val voovieService:VoovieService,
    private val voovieDao:VoovieDao
){
/**
 * Get movies from VoovieDatabase*/
    fun getMovies(): Flow<List<Movie>>{
        return voovieDao.getMovies()
    }
    /**
     * search for movies and save results to VoovieDatabase*/
    suspend fun searchMovies(query:String){
        val response:Result = voovieService.searchMovies(query)
        val movies = response.results.map { movie ->
          id = movie.id,
            title = movie.title,
            overview = movie.overview,
            posterPath = movie.posterPath,
            genres = movie.genres,
            releaseDate = movie.releaseDate,
            voteAverage = movie.voteAverage
        }
    }
  voovieDao.insertMovies(movie)
}
