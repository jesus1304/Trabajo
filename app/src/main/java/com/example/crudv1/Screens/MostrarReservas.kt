package com.example.crudv1.Screens

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.navigation.SessionManager
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarReservas(navController: NavHostController) {
    var clienteEncontrado by remember { mutableStateOf(false) }


    data class Reserva(
        val direccion: String,
        val user:String,
        val fecha: String,
        val precio: String,
        val Nif: String


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
                    Text("Facturas", color = Color.White)
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
        bottomBar = {

        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
            .background(Color(12, 12, 12)),

        verticalArrangement = Arrangement.spacedBy(10.dp)

        ) {
            var datos by remember { mutableStateOf("") }
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                val db = FirebaseFirestore.getInstance()
                val coleccion = "factura"
                val nombreUsuario = SessionManager.getUsername(context)
                val fechaActual = Calendar.getInstance().time

                db.collection(coleccion)
                    .whereEqualTo("user", nombreUsuario)
                    .get()
                    .addOnSuccessListener { resultado ->
                        val reservasUsuario = resultado.documents.map { cliente ->
                            Reserva(
                                fecha = cliente.getString("fecha") ?: "",
                                direccion = cliente.getString("direccion") ?: "",
                                Nif = cliente.getString("Nif") ?: "",
                                user = cliente.getString("user") ?: "",
                                precio = cliente.getString("precio") ?: ""
                            )
                        }.filter {  it.fecha > SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(fechaActual) }
                            .sortedBy { it.fecha }

                        reservas.value = reservasUsuario
                        clienteEncontrado = reservasUsuario.isNotEmpty()
                    }
                    .addOnFailureListener {
                        datos = "No ha podido conectar"
                    }
            }



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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(7.dp)
            ) {
                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Select date",
                        color = Color.White) },
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
                if ( fecha.isNotBlank()) {
                    reservas.value.filter {
                                (it.fecha.equals(
                                    fecha,
                                    ignoreCase = true
                                ) || fecha.isBlank())
                    }
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
                            text = "Nombre de Usuario: ${reserva.user}",
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

                    val db = FirebaseFirestore.getInstance()
                    val coleccion = "factura"
                    var user by remember { mutableStateOf("") }
                    var mensajeConfirmacion by remember { mutableStateOf("") }
                    var fechaSeleccionada by remember { mutableStateOf("") }
                    var precioSeleccionada by remember { mutableStateOf("") }
                    var direccionSeleccionada by remember { mutableStateOf("") }
                    var NifSeleccionada by remember { mutableStateOf("") }
                    var showDialog by remember { mutableStateOf(false) }

                    Button(
                        onClick = {
                            fechaSeleccionada = reserva.fecha
                            precioSeleccionada = reserva.precio
                            direccionSeleccionada = reserva.direccion
                            NifSeleccionada = reserva.Nif
                            user = reserva.user
                            showDialog = true
                        },
                        modifier = Modifier.padding(start = 100.dp,end = 100.dp,top=10.dp, bottom = 10.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = (Color(100, 97, 117)),
                            contentColor = Color.White
                        ),
                    ) {
                        Text(
                            text = "Cancelar Reserva",
                            fontSize = 18.sp
                        )
                    }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text("Confirmar eliminación") },
                            text = { Text("¿Estás seguro de eliminar esta reserva?") },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        val nombreUsuario = SessionManager.getUsername(context)

                                        db.collection(coleccion)
                                            .whereEqualTo("user", nombreUsuario)
                                            .whereEqualTo("fecha", fechaSeleccionada)
                                            .whereEqualTo("direccion", direccionSeleccionada)
                                            .whereEqualTo("precio", precioSeleccionada)
                                            .whereEqualTo("Nif", NifSeleccionada)

                                            .get()
                                            .addOnSuccessListener { querySnapshot ->
                                                for (document in querySnapshot) {
                                                    db.collection(coleccion)
                                                        .document(document.id)
                                                        .delete()
                                                        .addOnSuccessListener {
                                                            mensajeConfirmacion = "Reserva cancelada correctamente"
                                                        }
                                                        .addOnFailureListener { exception ->
                                                            mensajeConfirmacion = "Error al cancelar la reserva: $exception"
                                                        }
                                                }
                                            }
                                            .addOnFailureListener { exception ->
                                                mensajeConfirmacion = "Error al buscar la reserva: $exception"
                                            }

                                        showDialog = false
                                    },

                                ) {
                                    Text("Confirmar")
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = { showDialog = false
                                    }
                                ) {
                                    Text("Cancelar")
                                }
                            }
                        )
                    }

                    if (mensajeConfirmacion.isNotEmpty()) {
                        Text(
                            text = mensajeConfirmacion,
                            modifier = Modifier.padding(top = 30.dp),
                            color = if (mensajeConfirmacion.startsWith("Error")) Color.Red else Color.Blue
                        )
                    }
                }
            }
        }
    }
}