package com.example.crudv1.Screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarFacturas2(navController: NavHostController) {
    var clienteEncontrado by remember { mutableStateOf(false) }


    data class Reserva(
        val direccion: String,
        val user: String,
        val fecha: String,
        val precio: Int,
        val Nif: String,
        val factura: String


    )

    var reservas = remember { mutableStateOf(emptyList<Reserva>()) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(12, 12, 12),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Tus reservas", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Facturas") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = Color.White

                        )
                    }
                },
                actions = {

                }
            )
        },

        ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(12, 12, 12))

        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .background(Color(12, 12, 12)),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                var datos by remember { mutableStateOf("") }
                val context = LocalContext.current
                LaunchedEffect(Unit) {
                    val db = FirebaseFirestore.getInstance()
                    val coleccion = "factura"
                    val fechaActual = Calendar.getInstance().time

                    db.collection(coleccion)
                        .get()
                        .addOnSuccessListener { resultado ->
                            val reservasUsuario = resultado.documents.map { cliente ->
                                Reserva(
                                    fecha = cliente.getString("fecha") ?: "",
                                    direccion = cliente.getString("direccion") ?: "",
                                    Nif = cliente.getString("Nif") ?: "",
                                    factura = cliente.getString("factura") ?: "",
                                    user = cliente.getString("user") ?: "",
                                    precio = cliente.getLong("precio")?.toInt() ?: 0
                                )
                            }.filter {
                                it.fecha > SimpleDateFormat(
                                    "yyyy/MM/dd",
                                    Locale.getDefault()
                                ).format(fechaActual) && it.factura == "Venta"
                            }
                                .sortedBy { it.fecha }
                            reservas.value = reservasUsuario
                            clienteEncontrado = reservasUsuario.isNotEmpty()
                        }
                        .addOnFailureListener {
                            datos = "No ha podido conectar"
                        }
                }


                var nombreFiltrar by remember { mutableStateOf("") }
                var fecha by remember { mutableStateOf("") }
                val mCalendar: Calendar = Calendar.getInstance()
                val anio: Int = mCalendar.get(Calendar.YEAR)
                val mes: Int = mCalendar.get(Calendar.MONTH)
                val dia: Int = mCalendar.get(Calendar.DAY_OF_MONTH)
                val mDatePickerDialog = DatePickerDialog(
                    LocalContext.current, { _: DatePicker, anio: Int, mes: Int, dia: Int ->
                        val mesFormateado = String.format("%02d", mes + 1)
                        val diaFormateado = String.format("%02d", dia)
                        fecha = "$anio/${mesFormateado}/$diaFormateado"
                    }, anio, mes, dia
                )
                TextField(
                    value = nombreFiltrar,
                    onValueChange = { nombreFiltrar = it },
                    label = { Text("Ingrese el cliente") },
                    modifier = Modifier
                        .padding(top = 20.dp, start = 5.dp)
                        .fillMaxWidth()
                        .background(Color(41, 40, 48)),
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(7.dp)
                ) {
                    OutlinedTextField(
                        value = fecha,
                        onValueChange = { fecha = it },
                        label = {
                            Text(
                                "Select date",
                                color = Color.White
                            )
                        },
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth()
                            .background(Color(41, 40, 48)),

                        singleLine = true,
                        leadingIcon = {
                            val dateIcon = Icons.Default.DateRange
                            Icon(
                                imageVector = dateIcon,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(44.dp)
                                    .clickable { mDatePickerDialog.show() }
                                    .align(Alignment.CenterVertically)
                            )
                        }
                    )
                }
                val reservasFiltradasPorNombreYFecha =
                    if (nombreFiltrar.isNotBlank() || fecha.isNotBlank()) {
                        reservas.value.filter {
                            (it.user.equals(
                                nombreFiltrar,
                                ignoreCase = true
                            ) || nombreFiltrar.isBlank()) &&
                                    (it.fecha.equals(
                                        fecha,
                                        ignoreCase = true
                                    ) || fecha.isBlank())
                        }.sortedWith(compareBy({ it.fecha }))
                    } else {
                        reservas.value.sortedWith(compareBy({ it.fecha }))
                    }

                for (reserva in reservasFiltradasPorNombreYFecha) {
                    Column(
                        modifier = Modifier
                            .padding(3.dp)
                            .border(5.dp, Color(45, 43, 50))
                            .background(Color(41, 40, 48)),
                    )
                    {
                        Divider(
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Gray,
                            thickness = 3.dp
                        )
                        Row() {

                            Text(
                                modifier = Modifier.padding(start = 50.dp, top = 10.dp),
                                text = "Nombre: ${reserva.user}",
                                fontSize = 20.sp,
                                color = Color.White

                            )
                            Text(
                                modifier = Modifier.padding(start = 100.dp, top = 10.dp),
                                text = "Tipo: ${reserva.factura}",
                                fontSize = 20.sp,
                                color = Color.White

                            )
                        }
                        Row() {

                            Text(
                                modifier = Modifier.padding(start = 50.dp, top = 10.dp),
                                text = "Fecha: ${reserva.fecha}",
                                fontSize = 20.sp,
                                color = Color.White
                            )
                            Text(
                                modifier = Modifier.padding(start = 30.dp, top = 10.dp),
                                text = "Direccion: ${reserva.direccion}",
                                fontSize = 20.sp,
                                color = Color.White

                            )
                        }
                        Row() {
                            Text(
                                modifier = Modifier.padding(start = 50.dp, top = 10.dp),
                                text = "Nif: ${reserva.Nif}",
                                fontSize = 20.sp,
                                color = Color.White
                            )
                            Text(
                                modifier = Modifier.padding(start = 133.dp, top = 10.dp),
                                text = "Precio: ${reserva.precio}",
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        }

                    }
                }
            }
        }
    }
}