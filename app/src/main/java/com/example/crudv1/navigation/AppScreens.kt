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

}