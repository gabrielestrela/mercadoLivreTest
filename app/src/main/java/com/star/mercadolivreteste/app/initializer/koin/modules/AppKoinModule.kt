package com.star.mercadolivreteste.app.initializer.koin.modules

import com.star.corekotlin.di.CoreKotlinModule
import com.star.mercadolivreteste.di.HomeKoinModule
import com.star.network.di.NetworkKoinModule
import org.koin.core.KoinApplication
import org.koin.core.module.Module

class AppKoinModule : ApplicationKoinModule {
    override val features: List<Module> = listOf()

    override val libraries: List<Module>
        get() = listOf()
    override val cache: List<Module>
        get() = listOf()

    override fun load() {
        HomeKoinModule().load()
        NetworkKoinModule.load()
        CoreKotlinModule.load()
    }

    fun modules(): List<Module> = libraries + features + cache
}
