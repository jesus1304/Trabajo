package com.example.crudv1.navigation

import Campos
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crudv1.Screens.Aqua
import com.example.crudv1.Screens.Bike
import com.example.crudv1.Screens.BorrarReserva
import com.example.crudv1.Screens.CambiarReserva
import com.example.crudv1.Screens.Clases
import com.example.crudv1.Screens.MenuInicio
import com.example.crudv1.Screens.ClienteGuardar
import com.example.crudv1.Screens.ClienteModificar
import com.example.crudv1.Screens.ClienteBorrar
import com.example.crudv1.Screens.ClienteConsultar
import com.example.crudv1.Screens.ClienteInforme
import com.example.crudv1.Screens.Ejercicios
import com.example.crudv1.Screens.EntrenadorBorrar
import com.example.crudv1.Screens.EntrenadorConsultar
import com.example.crudv1.Screens.EntrenadorGuardar
import com.example.crudv1.Screens.EntrenadorInforme
import com.example.crudv1.Screens.EntrenadorModificar
import com.example.crudv1.Screens.Fitness
import com.example.crudv1.Screens.Futbol
import com.example.crudv1.Screens.Gimnasio
import com.example.crudv1.Screens.Health
import com.example.crudv1.Screens.Inicio
import com.example.crudv1.Screens.Inicio2
import com.example.crudv1.Screens.InicioSesion
import com.example.crudv1.Screens.MenuInicio2
import com.example.crudv1.Screens.MenuInicio3
import com.example.crudv1.Screens.MostrarReservas
import com.example.crudv1.Screens.MostrarReservas2
import com.example.crudv1.Screens.MostrarReservasGym
import com.example.crudv1.Screens.Piscina
import com.example.crudv1.Screens.PiscinaCerrada
import com.example.crudv1.Screens.Puntos
import com.example.crudv1.Screens.ReservaClases
import com.example.crudv1.Screens.ReservaStep
import com.example.crudv1.Screens.ReservasFutbol
import com.example.crudv1.Screens.StepFuncional
import com.example.crudv1.Screens.VendedorBorrar
import com.example.crudv1.Screens.VendedorConsultar
import com.example.crudv1.Screens.VendedorGuardar
import com.example.crudv1.Screens.VendedorInforme
import com.example.crudv1.Screens.VendedorModificar
import com.example.crudv1.Screens.Yoga

@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.InicioSesion.ruta)
    {
        composable(AppScreens.MenuInicio.ruta) { MenuInicio(navigationController) }
        composable(AppScreens.Inicio.ruta) { Inicio(navigationController) }
        composable(AppScreens.ClienteGuardar.ruta) { ClienteGuardar(navigationController) }
        composable(AppScreens.MenuInicio2.ruta) { MenuInicio2(navigationController) }
        composable(AppScreens.MenuInicio3.ruta) { MenuInicio3(navigationController) }
        composable(AppScreens.ClienteModificar.ruta) { ClienteModificar(navigationController) }
        composable(AppScreens.ClienteBorrar.ruta) { ClienteBorrar(navigationController) }
        composable(AppScreens.ClienteConsultar.ruta) { ClienteConsultar(navigationController) }
        composable(AppScreens.ClienteInforme.ruta) { ClienteInforme(navigationController) }
        composable(AppScreens.VendedorGuardar.ruta) { VendedorGuardar(navigationController) }
        composable(AppScreens.VendedorModificar.ruta) { VendedorModificar(navigationController) }
        composable(AppScreens.VendedorBorrar.ruta) { VendedorBorrar(navigationController) }
        composable(AppScreens.VendedorConsultar.ruta) { VendedorConsultar(navigationController) }
        composable(AppScreens.VendedorInforme.ruta) { VendedorInforme(navigationController) }
        composable(AppScreens.EntrenadorGuardar.ruta) { EntrenadorGuardar(navigationController) }
        composable(AppScreens.EntrenadorModificar.ruta) { EntrenadorModificar(navigationController) }
        composable(AppScreens.EntrenadorBorrar.ruta) {EntrenadorBorrar(navigationController) }
        composable(AppScreens.EntrenadorConsultar.ruta) { EntrenadorConsultar(navigationController) }
        composable(AppScreens.EntrenadorInforme.ruta) { EntrenadorInforme(navigationController) }
        composable(AppScreens.InicioSesion.ruta) { InicioSesion(navigationController) }
        composable(AppScreens.Inicio2.ruta) { Inicio2(navigationController) }
        composable(AppScreens.Campos.ruta) { Campos(navigationController) }
        composable(AppScreens.Piscina.ruta) { Piscina(navigationController) }
        composable(AppScreens.BorrarReserva.ruta) { BorrarReserva(navigationController) }
        composable(AppScreens.CambiarReserva.ruta) { CambiarReserva(navigationController) }
        composable(AppScreens.PiscinaCerrada.ruta) { PiscinaCerrada(navigationController) }
        composable(AppScreens.Gimnasio.ruta) { Gimnasio(navigationController) }
        composable(AppScreens.StepFuncional.ruta) { StepFuncional(navigationController) }
        composable(AppScreens.ReservaStep.ruta) { ReservaStep(navigationController) }
        composable(AppScreens.Clases.ruta) { Clases(navigationController) }
        composable(AppScreens.ReservaClases.ruta) { ReservaClases(navigationController) }
        composable(AppScreens.MostrarReservasGym.ruta) { MostrarReservasGym(navigationController) }
        composable(AppScreens.MostrarReservas.ruta) { MostrarReservas(navigationController) }
        composable(AppScreens.Bike.ruta) { Bike(navigationController) }
        composable(AppScreens.Health.ruta) { Health(navigationController) }
        composable(AppScreens.Aqua.ruta) { Aqua(navigationController) }
        composable(AppScreens.Fitness.ruta) { Fitness(navigationController) }
        composable(AppScreens.Yoga.ruta) { Yoga(navigationController) }
        composable(AppScreens.Ejercicios.ruta) { Ejercicios(navigationController) }
        composable(AppScreens.MostrarReservas2.ruta) { MostrarReservas2(navigationController) }
        composable(AppScreens.Puntos.ruta) { Puntos(navigationController) }
        composable(AppScreens.Futbol.ruta) { Futbol(navigationController) }
        composable(AppScreens.ReservasFutbol.ruta) { ReservasFutbol(navigationController) }


    }
}