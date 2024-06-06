package com.example.githubclient

import android.app.Application
import com.example.githubclient.navigation.AndroidScreens
import com.example.githubclient.navigation.IScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class MyApp : Application() {
    companion object {
        lateinit var instance: MyApp
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router
    val screens : IScreens = AndroidScreens()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}