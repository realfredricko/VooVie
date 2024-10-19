package com.example.voovie.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface VoovieService {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = "API_KEY"
    )
}