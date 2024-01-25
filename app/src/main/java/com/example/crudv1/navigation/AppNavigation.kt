package com.example.crudv1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.crudv1.Screens.MenuInicio
import com.example.crudv1.Screens.ClienteGuardar
import com.example.crudv1.Screens.ClienteModificar
import com.example.crudv1.Screens.Facturas
import com.example.crudv1.Screens.Inicio2
import com.example.crudv1.Screens.InicioSesion
import com.example.crudv1.Screens.MostrarFacturas
import com.example.crudv1.Screens.MostrarReservas
import com.example.crudv1.Screens.StartNow
import com.example.crudv1.Screens.Login
import com.example.crudv1.Screens.MenuPPAL
import com.example.crudv1.Screens.MostrarProveedores
import com.example.crudv1.Screens.Proveedores


@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.MenuPPAL.ruta)
    {
        composable(AppScreens.MenuInicio.ruta) { MenuInicio(navigationController) }
        composable(AppScreens.ClienteGuardar.ruta) { ClienteGuardar(navigationController) }
        composable(AppScreens.ClienteModificar.ruta) { ClienteModificar(navigationController) }
        composable(AppScreens.InicioSesion.ruta) { InicioSesion(navigationController) }
        composable(AppScreens.Inicio2.ruta) { Inicio2(navigationController) }
        composable(AppScreens.Facturas.ruta) { Facturas(navigationController) }
        composable(AppScreens.MostrarReservas.ruta) { MostrarReservas(navigationController) }
        composable(AppScreens.MostrarFacturas.ruta) { MostrarFacturas(navigationController) }
        composable(AppScreens.StartNow.ruta) { StartNow(navigationController) }
        composable(AppScreens.Login.ruta){ Login(navigationController)}
        composable(AppScreens.MenuPPAL.ruta){ MenuPPAL(navigationController) }
        composable(AppScreens.Proveedores.ruta){ Proveedores(navigationController) }
        composable(AppScreens.MostrarProveedores.ruta){ MostrarProveedores(navigationController) }

    }
}