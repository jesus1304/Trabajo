package com.example.crudv1.Retrofit

data class Cliente(
    var idCliente: Int,
    var nombre: String,
    var apellido: String,
    var telefono: String,
    var correo: String,
    var user: String,
    var contraseña: String
)