package com.example.formunavegacion.navegate

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.formunavegacion.ui.screen.BienvenidaScreen
import com.example.formunavegacion.ui.screen.LoginScreen
import com.example.formunavegacion.viewmodel.UsuarioViewModel

@Composable

fun AppNavigate(){
    val navController = rememberNavController()
    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable(route = "login") {
            LoginScreen(usuarioViewModel,navController)
        }
        composable(route = "bienvenida") {
            BienvenidaScreen(usuarioViewModel)
        }
    }
}