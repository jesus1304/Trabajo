package com.example.crudv1.Retrofit

data class Factura(
    var precio: Int,
    var factura: String,
    var Nif: String,
    var direccion: String,
    var fecha: String,
    var user: String
)