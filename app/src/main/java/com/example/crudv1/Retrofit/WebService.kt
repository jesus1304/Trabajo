package com.example.crudv1.Retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WebService {
    @GET("/clientes")
    suspend fun getClientes(): Response<ClientesResponse>

    @POST("/clientes/agregar")
    suspend fun addUsuarios(
        @Body cliente: Cliente
    ): Response<ClientesResponse>

    @PUT("/clientes/actualizar/{id}")
    suspend fun updateUsuario(
        @Path("id") idCliente: String,
        @Body cliente: Cliente
    ): Response<ClientesResponse>

    @DELETE("/clientes/borrar/{id}")
    suspend fun deleteUsuario(
        @Path("id") idCliente: String
    ): Response<ClientesResponse>
}