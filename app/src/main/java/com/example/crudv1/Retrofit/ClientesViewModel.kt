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
}