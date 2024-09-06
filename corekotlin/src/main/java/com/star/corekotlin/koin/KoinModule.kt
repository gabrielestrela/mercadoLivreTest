package com.star.corekotlin.koin

import org.koin.core.module.Module

interface KoinModule {
    val data: Module
    val domain: Module
    val presentation: Module
    val additional: Module

    fun load()
    fun unload()
    fun getModules(): List<Module>
}