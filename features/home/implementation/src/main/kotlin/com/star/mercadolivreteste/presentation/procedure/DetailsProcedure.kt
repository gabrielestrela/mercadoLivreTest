package com.star.mercadolivreteste.presentation.procedure

sealed interface DetailsProcedure {
    data class SetDetails(val title: String, val price: String) : DetailsProcedure
}