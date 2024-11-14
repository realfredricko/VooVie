package com.example.voovie

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
//Manages dependency injection across the app
@HiltAndroidApp
class BaseApplication:Application() {
}