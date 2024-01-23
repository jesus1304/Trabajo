package com.example.crudv1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crudv1.Screens.MenuInicio
import com.example.crudv1.Screens.ClienteGuardar
import com.example.crudv1.Screens.ClienteModificar
import com.example.crudv1.Screens.Inicio2
import com.example.crudv1.Screens.InicioSesion
import com.example.crudv1.Screens.MostrarReservas
import com.example.crudv1.Screens.Piscina
import com.example.crudv1.Screens.StartNow


@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.StartNow.ruta)
    {
        composable(AppScreens.MenuInicio.ruta) { MenuInicio(navigationController) }
        composable(AppScreens.ClienteGuardar.ruta) { ClienteGuardar(navigationController) }
        composable(AppScreens.ClienteModificar.ruta) { ClienteModificar(navigationController) }
        composable(AppScreens.InicioSesion.ruta) { InicioSesion(navigationController) }
        composable(AppScreens.Inicio2.ruta) { Inicio2(navigationController) }
        composable(AppScreens.Piscina.ruta) { Piscina(navigationController) }
        composable(AppScreens.MostrarReservas.ruta) { MostrarReservas(navigationController) }
        composable(AppScreens.StartNow.ruta) { StartNow(navigationController) }



    }
}