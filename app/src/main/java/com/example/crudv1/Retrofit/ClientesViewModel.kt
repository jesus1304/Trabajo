package com.example.crudv1.Retrofit

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
            val response = RetrofitClient.webService.guardarCliente(cliente)
            withContext(Dispatchers.Main) {
                if(response.body()!!.codigo == "200") {
                    listarClientes()
                }
            }
        }
    }

    fun actualizarCliente(idCliente: String, cliente: Cliente) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.actualizarCliente(idCliente, cliente)
            withContext(Dispatchers.Main) {
                if(response.body()!!.codigo == "200") {
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