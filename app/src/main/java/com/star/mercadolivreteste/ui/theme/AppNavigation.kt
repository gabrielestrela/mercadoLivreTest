package com.star.mercadolivreteste.ui.theme

sealed class AppNavigationRoute(
    val route: String,
    val title: String
) {
    data class HomeRoute(
        val homeRoute: String = "HomeRoute",
        val homeTitle: String = "Home"
    ) : AppNavigationRoute(route = homeRoute, title = homeTitle)

    data class DetailsRoute(
        val detailsRoute: String = "DetailsRoute",
        val detailsTitle: String = "Details"
    ) : AppNavigationRoute(route = detailsRoute, title = detailsTitle)
}