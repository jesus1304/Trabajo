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
    object StartNow : AppScreens("StartNow")
    object Login : AppScreens("Login")
    object MenuPPAL : AppScreens("MenuPPAL")
    object Ajustes : AppScreens("MostrarProveedores")

    object Proveedores : AppScreens("Proveedores")
    object MostrarProveedores : AppScreens("MostrarProveedores")


}