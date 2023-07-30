package com.berivan.movieapp

import android.app.Application
import com.berivan.movieapp.util.LocalDataManager

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startLocalDataManager()
    }

    private fun startLocalDataManager() {
        LocalDataManager.init(applicationContext)
    }
}