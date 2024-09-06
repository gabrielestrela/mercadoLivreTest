package com.star.mercadolivreteste.app.initializer

import android.content.Context
import androidx.startup.Initializer
import com.star.mercadolivreteste.app.initializer.koin.KoinInitializer

class AppInitializer : Initializer<Unit> {
    override fun create(context: Context) { /*Do Nothing*/ }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf(
        KoinInitializer::class.java,
    )
}