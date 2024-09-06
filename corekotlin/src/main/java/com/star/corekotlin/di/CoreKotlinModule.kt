package com.star.corekotlin.di

import com.star.corekotlin.coroutine.DefaultDispatcherProvider
import com.star.corekotlin.coroutine.DispatcherProvider
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object CoreKotlinModule {
    private val coreKotlinModule = module {
        factoryOf<DispatcherProvider>(::DefaultDispatcherProvider)
    }

    fun load() {
        loadKoinModules(coreKotlinModule)
    }
}