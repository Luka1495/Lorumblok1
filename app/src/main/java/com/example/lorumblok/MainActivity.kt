package com.example.lorumblok

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lorumblok.ui.NavRoutes
import com.example.lorumblok.ui.history.HistoryScreen
import com.example.lorumblok.ui.login.LoginScreen
import com.example.lorumblok.ui.newgame.NewGameScreen
import com.example.lorumblok.ui.registration.RegistrationScreen
import com.example.lorumblok.ui.standings.StandingsScreen
import com.example.lorumblok.ui.theme.LorumBlokTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LorumBlokTheme {
                val navController = rememberNavController()
                val mainViewModel:MainViewModel= viewModel()
                NavHost(navController = navController, startDestination = NavRoutes.LOGIN.route) {
                    composable(NavRoutes.STANDINGS.route) { StandingsScreen(navController) }
                    composable(NavRoutes.NEW_GAME.route) { NewGameScreen(navController) }
                    composable(NavRoutes.HISTORY.route){ HistoryScreen(navController)}
                    composable(NavRoutes.LOGIN.route){ LoginScreen(navController)}
                    composable(NavRoutes.REGISTRATION.route){RegistrationScreen(navController)}
                }
            }
        }
    }
}

