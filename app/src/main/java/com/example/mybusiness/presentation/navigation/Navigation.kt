package com.example.mybusiness.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mybusiness.presentation.navigation.Screens.Companion.CARD_NUMBER
import com.example.mybusiness.presentation.screens.card.ToDoCardScreen
import com.example.mybusiness.presentation.screens.main.MainScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .statusBarsPadding(),

    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            navController = navController,
            startDestination = Screens.MainScreen.destination()
        ) {
            composable(route = Screens.MainScreen.destination()) {
                MainScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNavigateToCard = { navController.navigate(Screens.ToDoScreen.destination(it.toString())) }
                )
            }
            composable(route = Screens.ToDoScreen.screenRoute) { navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getString(CARD_NUMBER)?.toInt()
                ToDoCardScreen(
                    id = id,
                    onBack = navController::popBackStack,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }

    }
}

sealed class Screens(
    val screenRoute: String
) {


    object MainScreen : Screens(
        screenRoute = mainScreenRoute,
    ) {
        fun destination(): String {
            return screenRoute
        }
    }

    object ToDoScreen : Screens(
        screenRoute = "$toDoDetailsScreen/{$CARD_NUMBER}",
    ) {
        fun destination(name: String): String {
            return "$toDoDetailsScreen/$name"
        }
    }


    companion object {
        const val CARD_NUMBER = "name"

        const val mainScreenRoute = "mainScreen"
        const val toDoDetailsScreen = "peopleDetailsScreen"
    }
}