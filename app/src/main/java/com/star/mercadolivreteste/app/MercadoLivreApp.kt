package com.star.mercadolivreteste.app

import android.app.Application
import android.content.Context
import androidx.startup.AppInitializer
import com.star.mercadolivreteste.app.initializer.koin.modules.AppKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MercadoLivreApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeAppDependencies()
    }

    private fun Context.initializeAppDependencies() {
//        startKoin {
//            androidLogger()
//            androidContext(this@MercadoLivreApp)
//            modules(AppKoinModule().modules())
//        }
        AppInitializer
            .getInstance(this)
            .initializeComponent(
                com.star.mercadolivreteste.app.initializer.AppInitializer::class.java
            )
    }
}