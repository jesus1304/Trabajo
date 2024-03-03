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
class ProveedorViewModel: ViewModel() {

    var _listaProveedores: ArrayList<Proveedor> by mutableStateOf(arrayListOf())

    fun listarProveedores() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitProveedor.webService.listarProveedores()
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    _listaProveedores = response.body()!!.data
                }
            }
        }
    }
    fun guardarProveedor(proveedor: Proveedor) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitProveedor.webService.guardarProveedor(proveedor)
                withContext(Dispatchers.Main) {
                    val body = response.body()
                    if (body != null && body.codigo == "200") {
                        listarProveedores()
                    }
                }
            } catch (e: Exception) {
                // Manejar el error aquí
                Log.e("Error", "Error al guardar el cliente: ${e.message}")
            }
        }
    }
}

