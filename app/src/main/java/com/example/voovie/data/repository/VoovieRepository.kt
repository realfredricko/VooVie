package com.example.voovie.data.repository

import com.example.voovie.data.local.daos.VoovieDao
import com.example.voovie.data.network.VoovieService
import javax.inject.Inject
/**
 * Acts as an intermediary between VoovieViewModel and data sources
 * (Room database and NETWORK REQUEST)
 * **/
class VoovieRepository @Inject constructor(
    private val voovieService:VoovieService,
    private val voovieDao:VoovieDao
){

}