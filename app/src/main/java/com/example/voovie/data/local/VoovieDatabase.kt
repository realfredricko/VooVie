package com.example.voovie.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.voovie.data.local.daos.VoovieDao
import com.example.voovie.data.model.Movie

@Database(entities = [Movie::class], version = 1,
    exportSchema = false)
class VoovieDatabase {
    abstract  class VoovieDatabase: RoomDatabase(){
        abstract fun voovieDao():VoovieDao
        companion object{
            private const val DATABASE_NAME = "voovie.db"
            /**
             * Only one instance of db in the app will used to store
            *This avoids memory leaks in android when there exist multiple instances of db
             **/
            @Volatile
            private  var INSTANCE:VoovieDatabase? = null
            fun getDatabase(context: Context):VoovieDatabase{
               return INSTANCE ?: synchronized(this) {
                   val instance = Room.databaseBuilder(
                       context.applicationContext,
                       VoovieDatabase::class.java,
                       DATABASE_NAME
                   ).build()
                   INSTANCE = instance
                   instance
               }
            }
        }
    }
}