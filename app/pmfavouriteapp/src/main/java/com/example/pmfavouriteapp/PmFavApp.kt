package com.example.pmfavouriteapp

import android.app.Application
import com.example.pmfavouriteapp.data.AppContainer

class MyApp : Application() {
    lateinit var container: AppContainer
        private set

    companion object {
        @Volatile
        private var instance: MyApp? = null

        // Safe accessor
        fun getInstance(): MyApp =
            instance ?: throw IllegalStateException("Application is not created yet")
    }

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
        instance = this
    }
}
