package com.example.crudv1.Retrofit

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class ClientesViewModel: ViewModel() {

    var _listaClientes: ArrayList<Cliente> by mutableStateOf(arrayListOf())

    fun listarClientes() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.listarClientes()
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    _listaClientes = response.body()!!.data
                }
            }
        }
    }

    fun guardarCliente(cliente: Cliente) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.guardarCliente(cliente)
                withContext(Dispatchers.Main) {
                    val body = response.body()
                    if (body != null && body.codigo == "200") {
                        listarClientes()
                    }
                }
            } catch (e: Exception) {
                // Manejar el error aquí
                Log.e("Error", "Error al guardar el cliente: ${e.message}")
            }
        }
    }


    fun actualizarCliente(idCliente: Int, cliente: Cliente) {
        val idClienteString = idCliente.toString() // Convertir Int a String
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.actualizarCliente(idCliente, cliente) // Pasar idClienteString en lugar de idCliente
            withContext(Dispatchers.Main) {
                if(response.body()?.codigo == "200") { // Usar el operador de navegación segura ?. para evitar NullPointerException
                    listarClientes()
                }
            }
        }
    }

    fun eliminarCliente(idCliente: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.eliminarCliente(idCliente)
            withContext(Dispatchers.Main) {
                if(response.body()!!.codigo == "200") {
                    listarClientes()
                }
            }
        }
    }
}