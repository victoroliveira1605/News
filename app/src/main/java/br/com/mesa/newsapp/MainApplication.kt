package br.com.mesa.newsapp

import android.app.Application
import br.com.mesa.newsapp.di.AppModule
import br.com.mesa.newsapp.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(AppModule, NetworkModule))
        }

    }
}