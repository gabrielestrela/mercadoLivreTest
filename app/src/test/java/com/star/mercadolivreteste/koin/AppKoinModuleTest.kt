package com.star.mercadolivreteste.koin

import com.star.mercadolivreteste.app.initializer.koin.modules.AppKoinModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

class AppKoinModuleTest {
    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun check() {
        AppKoinModule().modules().forEach { it.verify() }
    }

}