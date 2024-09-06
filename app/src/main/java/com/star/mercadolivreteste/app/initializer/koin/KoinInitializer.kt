package com.star.mercadolivreteste.app.initializer.koin

import android.content.Context
import androidx.startup.Initializer
import com.star.mercadolivreteste.app.initializer.koin.modules.AppKoinModule
import com.star.mercadolivreteste.di.HomeKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class KoinInitializer : Initializer<KoinApplication> {

    private val koinModules = AppKoinModule()

    override fun create(context: Context): KoinApplication = startKoin {
        androidContext(context)
        androidLogger()
//        loadKoinModules(koinModules.modules())
        with(koinModules) {
            load()
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}