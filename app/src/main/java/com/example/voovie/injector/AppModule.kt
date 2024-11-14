package com.example.voovie.injector

import android.content.Context
import androidx.room.Room
import com.example.voovie.data.local.VoovieDatabase
import com.example.voovie.data.local.daos.VoovieDao
import com.example.voovie.data.network.VoovieService
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Class where how dependencies should be provided is defined
  **/
/**
 * This module will provide instances of
 * [VoovieDatabase],[VoovieService], [VoovieDao]*/
object AppModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    //private const val VOOVIE_DATABASE = "voovie_database"
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideVoovieService(retrofit: Retrofit):VoovieService {
        return retrofit.create(VoovieService::class.java)
    }
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):VoovieDatabase{
        return Room.databaseBuilder(
                context,
        VoovieDatabase::class.java,
        "voovie_database"
        ).build()
    }
    @Provides
    fun provideVoovieDao(db: VoovieDatabase): VoovieDao = db.getVoovieDao()
    }
