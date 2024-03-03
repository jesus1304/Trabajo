package com.example.crudv1.Retrofit

data class ProveedorResponse(
    var codigo: String,
    var mensaje: String,
    var data: ArrayList<Proveedor>)