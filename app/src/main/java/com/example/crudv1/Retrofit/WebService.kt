package com.example.crudv1.Retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WebService {
    @GET("/listarClientes")
    suspend fun listarClientes(): Response<ClientesResponse>

    @GET("/listarFacturas")
    suspend fun listarFacturas(): Response<FacturaResponse>

    @POST("/guardarCliente")
    suspend fun guardarCliente(
        @Body cliente: Cliente
    ): Response<ClientesResponse>
    @GET("/listarProveedores")
    suspend fun listarProveedores(): Response<ProveedorResponse>

    @POST("/guardarProveedor")
    suspend fun guardarProveedor(
        @Body proveedor: Proveedor
    ): Response<ClientesResponse>

    @PUT("/actualizarCliente/{idCliente}")
    suspend fun actualizarCliente(
        @Path("id") idCliente: String,
        @Body cliente: Cliente
    ): Response<ClientesResponse>

    @DELETE("/eliminarCliente/{idCliente}")
    suspend fun eliminarCliente(
        @Path("id") idCliente: String
    ): Response<ClientesResponse>
}