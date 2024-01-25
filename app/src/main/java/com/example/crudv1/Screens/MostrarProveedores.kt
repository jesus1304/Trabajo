package com.example.crudv1.Screens

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
fun MostrarProveedores(navController: NavHostController) {
    var clienteEncontrado by remember { mutableStateOf(false) }


    data class Reserva(
        val nombre: String,
        val apellido: String,
        val correo: String,
        val telefono: String


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
                    Text("Lista Proveedores", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Proveedores") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = Color.White

                        )
                    }
                },

                )
        },
        bottomBar = {

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

                verticalArrangement = Arrangement.spacedBy(10.dp)

            ) {
                var apellido by remember { mutableStateOf("") }
                var datos by remember { mutableStateOf("") }
                LaunchedEffect(Unit) {
                    val db = FirebaseFirestore.getInstance()
                    val coleccion = "proveedor"

                    db.collection(coleccion)
                        .get()
                        .addOnSuccessListener { resultado ->
                            val reservasUsuario = resultado.documents.map { cliente ->
                                Reserva(
                                    nombre = cliente.getString("nombre") ?: "",
                                    apellido = cliente.getString("apellido") ?: "",
                                    correo = cliente.getString("correo") ?: "",
                                    telefono = cliente.getString("telefono") ?: "",
                                )
                            }

                            reservas.value = reservasUsuario
                            clienteEncontrado = reservasUsuario.isNotEmpty()
                        }
                        .addOnFailureListener {
                            datos = "No ha podido conectar"
                        }
                }
                var nombreFiltrar by remember { mutableStateOf("") }
                TextField(
                    value = nombreFiltrar,
                    onValueChange = { nombreFiltrar = it },
                    label = { Text("Ingrese el proveedor") },
                    modifier = Modifier
                        .padding(top = 20.dp, start = 5.dp)
                        .fillMaxWidth()
                        .background(Color(41, 40, 48)),
                )

                val reservasFiltradasPorNombre =
                    if (nombreFiltrar.isNotBlank()) {
                        reservas.value.filter {
                            it.nombre.equals(nombreFiltrar, ignoreCase = true)
                        }
                    } else {
                        reservas.value // Devuelve todas las reservas si nombreFiltrar está en blanco
                    }

                for (reserva in reservasFiltradasPorNombre) {
                    // Haz lo que necesites con cada reserva


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
                                modifier = Modifier.padding(start = 30.dp, top = 10.dp),
                                text = "Nombre: ${reserva.nombre}",
                                fontSize = 20.sp,
                                color = Color.White

                            )
                            Text(
                                modifier = Modifier.padding(start = 30.dp, top = 10.dp),
                                text = "Apellido: ${reserva.apellido}",
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        }
                        Row() {


                            Text(
                                modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                                text = "Correo: ${reserva.correo}",
                                fontSize = 20.sp,
                                color = Color.White

                            )
                            Text(
                                modifier = Modifier.padding(start = 30.dp, top = 10.dp),
                                text = "Telefono: ${reserva.telefono}",
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        }


                        val db = FirebaseFirestore.getInstance()
                        val coleccion = "proveedor"
                        var mensajeConfirmacion by remember { mutableStateOf("") }
                        var nombreSeleccionada by remember { mutableStateOf("") }
                        var apellidoSeleccionada by remember { mutableStateOf("") }
                        var correoSeleccionada by remember { mutableStateOf("") }
                        var telefonoSeleccionada by remember { mutableStateOf("") }
                        var showDialog by remember { mutableStateOf(false) }

                        Button(
                            onClick = {
                                nombreSeleccionada = reserva.nombre
                                apellidoSeleccionada = reserva.apellido
                                correoSeleccionada = reserva.correo
                                telefonoSeleccionada = reserva.telefono
                                showDialog = true
                            },
                            modifier = Modifier.padding(
                                start = 100.dp,
                                end = 100.dp,
                                top = 10.dp,
                                bottom = 10.dp
                            )
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = (Color(100, 97, 117)),
                                contentColor = Color.White
                            ),
                        ) {
                            Text(
                                text = "Eliminar Proveedor",
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

                                            db.collection(coleccion)
                                                .whereEqualTo("nombre", nombreSeleccionada)
                                                .whereEqualTo("apellido", apellidoSeleccionada)
                                                .whereEqualTo("correo", correoSeleccionada)
                                                .whereEqualTo("telefono", telefonoSeleccionada)

                                                .get()
                                                .addOnSuccessListener { querySnapshot ->
                                                    for (document in querySnapshot) {
                                                        db.collection(coleccion)
                                                            .document(document.id)
                                                            .delete()
                                                            .addOnSuccessListener {
                                                                mensajeConfirmacion =
                                                                    "Reserva cancelada correctamente"
                                                            }
                                                            .addOnFailureListener { exception ->
                                                                mensajeConfirmacion =
                                                                    "Error al cancelar la reserva: $exception"
                                                            }
                                                    }
                                                }
                                                .addOnFailureListener { exception ->
                                                    mensajeConfirmacion =
                                                        "Error al buscar la reserva: $exception"
                                                }

                                            showDialog = false
                                        },

                                        ) {
                                        Text("Confirmar")
                                    }
                                },
                                dismissButton = {
                                    Button(
                                        onClick = {
                                            showDialog = false
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
}