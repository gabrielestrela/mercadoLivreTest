package com.star.mercadolivreteste

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.star.mercadolivreteste.presentation.ui.DetailsScreen
import com.star.mercadolivreteste.presentation.ui.HomeScreen
import com.star.mercadolivreteste.ui.theme.AppNavigationRoute
import com.star.mercadolivreteste.ui.theme.MercadoLivreTesteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MercadoLivreTesteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = AppNavigationRoute.HomeRoute().homeRoute
                    ) {
                        composable(route = AppNavigationRoute.HomeRoute().homeRoute) {
                            HomeScreen(
                                toDetailsScreen = { title, price ->
                                    navController.navigate(
                                        route = "${AppNavigationRoute.DetailsRoute().detailsRoute}/$title/$price"
                                    )
                                },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable(route = "${AppNavigationRoute.DetailsRoute().detailsRoute}/{title}/{price}") { navBackStackEntry ->
                            val title = navBackStackEntry.arguments?.getString("title")
                            val price = navBackStackEntry.arguments?.getString("price")

                            title?.let { _title ->
                                price?.let { _price ->
                                    DetailsScreen(
                                        modifier = Modifier.padding(innerPadding),
                                        title =_title,
                                        price = _price
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MercadoLivreTesteTheme {
        Greeting("Android")
    }
}