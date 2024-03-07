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
class FacturaViewModel: ViewModel() {

    var _listaFacturas: ArrayList<Factura> by mutableStateOf(arrayListOf())

    fun listarFacturas() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.listarFacturas()
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    _listaFacturas = response.body()!!.data
                }
            }
        }
    }

}