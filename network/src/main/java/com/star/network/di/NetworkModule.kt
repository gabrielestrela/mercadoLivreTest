package com.star.network.di

import com.star.network.client.KtorHttpClient
import com.star.network.client.MercadoLivreClient
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object NetworkKoinModule {
    private val networkModule = module {
        singleOf(::MercadoLivreClient)
    }

    fun load() {
        loadKoinModules(networkModule)
    }
    fun getModule() = networkModule
}
