package com.star.mercadolivreteste.app.initializer.koin.modules

import org.koin.core.KoinApplication
import org.koin.core.module.Module

interface ApplicationKoinModule {
    val features: List<Module>
    val libraries: List<Module>
    val cache: List<Module>

    fun load()
}