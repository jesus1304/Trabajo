package com.example.crudv1.navigation

sealed class AppScreens(val ruta:String) {
    object MenuInicio : AppScreens("MenuInicio")
    object ClienteModificar: AppScreens("ClienteModificar")
    object ClienteGuardar : AppScreens("ClienteGuardar")
    object InicioSesion : AppScreens("InicioSesion")
    object Inicio2 : AppScreens("Inicio2")
    object Facturas : AppScreens("Facturas")
    object MostrarReservas : AppScreens("MostrarReservas")
    object MostrarFacturas : AppScreens("MostrarFacturas")

    object Bike : AppScreens("Bike")
    object Health : AppScreens("Health")
    object Aqua : AppScreens("Aqua")
    object Fitness : AppScreens("Fitness")
    object Yoga : AppScreens("Yoga")
    object Ejercicios : AppScreens("Ejercicios")
    object MostrarReservas2 : AppScreens("MostrarReservas2")
    object Puntos : AppScreens("Puntos")
    object Futbol : AppScreens("Futbol")
    object ReservasFutbol : AppScreens("ReservasFutbol")
    object StartNow : AppScreens("StartNow")
    object Login : AppScreens("Login")



}