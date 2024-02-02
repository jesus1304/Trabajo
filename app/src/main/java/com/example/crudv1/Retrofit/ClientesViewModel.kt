package com.example.crudv1.Retrofit

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
class ClientesViewModel: ViewModel() {

    var _listaClientes: ArrayList<Cliente> = java.util.ArrayList() by mutableStateOf(arrayListOf())

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
}}