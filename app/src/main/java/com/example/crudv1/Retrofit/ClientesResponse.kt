package com.example.crudv1.Retrofit

data class ClientesResponse(
    var codigo: String,
    var mensaje: String,
    var data: ArrayList<Cliente>)