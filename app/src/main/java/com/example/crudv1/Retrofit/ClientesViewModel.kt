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

    fun getClientes() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getClientes()
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    _listaClientes = response.body()!!.data
                }
            }
        }
    }

    fun addCliente(cliente: Cliente) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.addClientes(cliente)
            withContext(Dispatchers.Main) {
                if(response.body()!!.codigo == "200") {
                    getClientes()
                }
            }
        }
    }

    fun updateCliente(idCliente: String, cliente: Cliente) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.updateClientes(idCliente, cliente)
            withContext(Dispatchers.Main) {
                if(response.body()!!.codigo == "200") {
                    getClientes()
                }
            }
        }
    }

    fun deleteCliente(idCliente: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.deleteClientes(idCliente)
            withContext(Dispatchers.Main) {
                if(response.body()!!.codigo == "200") {
                    getClientes()
                }
            }
        }
    }
}