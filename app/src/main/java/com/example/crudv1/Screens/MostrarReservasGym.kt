package com.example.crudv1.Screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarReservasGym(navController: NavHostController) {
    var clienteEncontrado by remember { mutableStateOf(false) }
    data class Reserva(
        val profesor: String,
        val sala: String,
        val nombre: String,
        val fecha: String,
        val horario: String
    )

    var reservas = remember { mutableStateOf(emptyList<Reserva>()) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Tus reservas")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Gimnasio") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {


                }
            )
        },

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()), // Agrega el desplazamiento vertical aquí
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            val context = LocalContext.current
            var datos by remember { mutableStateOf("") }
            LaunchedEffect(Unit) {
                val db = FirebaseFirestore.getInstance()
                val coleccion = "clasesReserva"
                val nombreUsuario = SessionManager.getUsername(context)
                val fechaActual = Calendar.getInstance().time

                db.collection(coleccion)
                    .whereEqualTo("user", nombreUsuario)
                    .get()
                    .addOnSuccessListener { resultado ->
                        val reservasUsuario = resultado.documents.map { cliente ->

                        Reserva(
                                fecha = cliente.getString("fecha") ?: "",
                                profesor = cliente.getString("profesor") ?: "",
                                sala = cliente.getString("sala") ?: "",
                                nombre = cliente.getString("nombre") ?: "",
                                horario = cliente.getString("horario") ?: ""
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
                label = { Text("Ingrese la clase") },
                modifier = Modifier.padding(top = 20.dp, start = 30.dp)
                    .fillMaxWidth()

            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                val dateIcon = Icons.Default.DateRange
                Icon(
                    imageVector = dateIcon,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { mDatePickerDialog.show() }
                        .align(Alignment.CenterVertically)
                )
                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Select date") },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

            }
            val reservasFiltradasPorNombreYFecha =
                if (nombreFiltrar.isNotBlank() || fecha.isNotBlank()) {
                    reservas.value.filter {
                        (it.nombre.equals(
                            nombreFiltrar,
                            ignoreCase = true
                        ) || nombreFiltrar.isBlank()) &&
                                (it.fecha.equals(
                                    fecha,
                                    ignoreCase = true
                                ) || fecha.isBlank())
                    }.sortedWith(compareBy({ it.fecha }, { it.horario }))
                } else {
                    reservas.value.sortedWith(compareBy({ it.fecha }, { it.horario }))
                }

            for (reserva in reservasFiltradasPorNombreYFecha) {
                val colorBorde = obtenerColorBorde(reserva.nombre)

                Column(
                    modifier = Modifier
                        .padding(3.dp)
                        .border(5.dp, colorBorde)
                        .background(Color.White)
                ) {

                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Gray,
                        thickness = 3.dp
                    )
                    Row()
                    {
                        Text(
                            modifier = Modifier.padding(start = 100.dp, top = 10.dp),
                            text = "Nombre: ${reserva.nombre}",
                            fontSize = 21.sp

                        )
                    }
                    Row() {

                        Text(
                            modifier = Modifier.padding(start = 50.dp, top = 10.dp),
                            text = "Fecha: ${reserva.fecha}",
                            fontSize = 18.sp
                        )
                        Text(
                            modifier = Modifier.padding(start = 50.dp, top = 10.dp),
                            text = "Hora: ${reserva.horario}",
                            fontSize = 18.sp
                        )
                    }

                    Row() {


                        Text(
                            modifier = Modifier.padding(start = 50.dp, top = 10.dp),
                            text = "Profesor: ${reserva.profesor}",
                            fontSize = 18.sp
                        )
                        Text(
                            modifier = Modifier.padding(start = 57.dp, top = 10.dp),
                            text = "Sala: ${reserva.sala}",
                            fontSize = 18.sp
                        )

                    }

                    val db = FirebaseFirestore.getInstance()
                    val coleccion = "clasesReserva"
                    var showDialog by remember { mutableStateOf(false) }
                    var mensajeConfirmacion by remember { mutableStateOf("") }
                    var horaSeleccionada by remember { mutableStateOf("") }
                    var fechaSeleccionada by remember { mutableStateOf("") }
                    var profesorSeleccionada by remember { mutableStateOf("") }
                    var salaSeleccionada by remember { mutableStateOf("") }
                    var nombreSeleccionada by remember { mutableStateOf("") }
                    var id by remember { mutableStateOf("") }

                    Button(
                        onClick = {
                            fechaSeleccionada = reserva.fecha
                            horaSeleccionada = reserva.horario
                            nombreSeleccionada = reserva.nombre
                            salaSeleccionada = reserva.sala
                            profesorSeleccionada = reserva.profesor

                            db.collection(coleccion)
                                .whereEqualTo("fecha", fechaSeleccionada)
                                .whereEqualTo("horario", horaSeleccionada)
                                .whereEqualTo("nombre", nombreSeleccionada)
                                .whereEqualTo("sala", salaSeleccionada)
                                .whereEqualTo("profesor", profesorSeleccionada)

                                .get()
                                .addOnSuccessListener { documents ->
                                    for (document in documents) {
                                        id = document.id // Actualizar el ID del documento a eliminar
                                        showDialog = true // Mostrar el diálogo de confirmación después de obtener el ID
                                    }
                                }
                                .addOnFailureListener { exception ->
                                    mensajeConfirmacion = "Error al obtener el ID del documento: $exception"
                                }
                        },
                        modifier = Modifier.padding(start = 100.dp,end = 100.dp,top=10.dp, bottom = 15.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorBorde,
                            contentColor = Color.White
                        ),
                    ) {
                        Text(
                            text = "Cancelar reserva ",
                            fontSize = 18.sp // Tamaño del texto, puedes ajustar el valor según tu preferencia
                        )
                    }
                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text("Confirmar eliminación") },
                            text = { Text("¿Estás seguro de eliminar estos datos?") },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        // Utilizar el ID obtenido para eliminar el documento
                                        db.collection(coleccion)
                                            .document(id)
                                            .delete()
                                            .addOnSuccessListener {
                                                mensajeConfirmacion = "Datos eliminados correctamente"
                                                fechaSeleccionada = ""
                                                horaSeleccionada = ""
                                                nombreSeleccionada = ""
                                                salaSeleccionada = ""
                                                profesorSeleccionada = ""
                                                showDialog = false
                                            }
                                            .addOnFailureListener { exception ->
                                                mensajeConfirmacion = "Error al eliminar: $exception"
                                                showDialog = false
                                            }
                                    }
                                ) {
                                    Text("Confirmar")
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = { showDialog = false }
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
