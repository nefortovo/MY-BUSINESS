package com.example.mybusiness.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mybusiness.presentation.screens.card.ToDoCardScreen
import com.example.mybusiness.presentation.screens.main.MainScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .statusBarsPadding(),

        ) { paddingValues ->
        NavHost(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            navController = navController,
            startDestination = Main
        ) {

            composable<Main> {
                MainScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNavigateToCard = {id ->
                        navController.navigate(ToDo(id)) }
                )
            }

            composable<ToDo> {backStackEntry ->
                val card: ToDo = backStackEntry.toRoute()
                ToDoCardScreen(
                    id = card.id,
                    onBack = navController::popBackStack,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }

    }
}

@Serializable
object Main

@Serializable
data class ToDo(val id: String)