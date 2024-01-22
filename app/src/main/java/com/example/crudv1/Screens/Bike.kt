package com.example.crudv1.Screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R
import com.example.crudv1.navigation.SessionManager
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bike(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    var optionsExpanded by remember { mutableStateOf(false) }
    val options = listOf(
        "Ver reserva",
        "Historial Reservas",
    )
    var clienteEncontrado by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedHour by remember { mutableStateOf("") }

    data class Reserva(
        val profesor: String,
        val sala: String,
        val nombre: String,
        val fecha: String,
        val horario: String,
        val user: String,
        val contador: Int
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
                    Text("Reservar Bike")
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

                    Button(onClick = { optionsExpanded = !optionsExpanded }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                    DropdownMenu(
                        expanded = optionsExpanded,
                        onDismissRequest = { optionsExpanded = false }) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option) },
                                onClick = {
                                    when (option) {
                                        "Ver reserva" -> navController.navigate("reservaStep")
                                        "Historial Reservas" -> navController.navigate("MostrarReservasGym")
                                    }
                                    optionsExpanded = false
                                }
                            )
                        }
                    }
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
            Image(
                painter = painterResource(id = R.drawable.bike2),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(250.dp)
            )
                    Text(
                        text = "  Beneficios de Bike\n" +
                                "\n" +
                                "1.Mejora en la técnica de pedaleo\n" +
                                "Aumenta la coordinación y el patrón motor para la cadenas musculares y cinética implicadas en el pedaleo.\n" +
                                "\n" +
                                "2.Mejora del sistema cardiovascular\n" +
                                "Aumenta el VO2 máximo y el segundo umbral.\n" +
                                "\n" +
                                "3.Reducción de grasa corporal\n" +
                                "Movilización del sistema lipídico.\n" +
                                "\n" +
                                "4.Aumento de la autoestima\n" +
                                "Favorece un entorno hormonal positivo.",
                        style = TextStyle(
                            fontSize = 19.sp
                        ),
                        modifier = Modifier
                            .padding(start=15.dp,end=15.dp)

                    )
            var datos by remember { mutableStateOf("") }
            LaunchedEffect(Unit) {
                val db = FirebaseFirestore.getInstance()
                val coleccion = "clases"

                db.collection(coleccion)
                    .get()
                    .addOnSuccessListener { resultado ->
                        val clientesList = resultado.documents
                        clienteEncontrado = clientesList.isNotEmpty()
                        reservas.value = clientesList.map { cliente ->
                            Reserva(
                                fecha = cliente.getString("fecha") ?: "",
                                profesor = cliente.getString("profesor") ?: "",
                                sala = cliente.getString("sala") ?: "",
                                nombre = cliente.getString("nombre") ?: "",
                                horario = cliente.getString("horario") ?: "",
                                user = cliente.getString("user") ?: "",
                                contador = cliente.getLong("contador")?.toInt() ?: 0
                            )
                        }.filter { it.nombre.equals("bicicleta", ignoreCase = true) } // Filtrar por nombre
                            .sortedByDescending { it.fecha }
                    }
                    .addOnFailureListener {
                        // Manejar error al conectar con la base de datos
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
                    fecha = "$dia/${mes + 1}/$anio"
                }, anio, mes, dia
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

                    var mensajeConfirmacion by remember { mutableStateOf("") }
                    var fechaSeleccionada by remember { mutableStateOf("") }
                    var horaSeleccionada by remember { mutableStateOf("") }
                    var profesorSeleccionado by remember { mutableStateOf("") }
                    var salaSeleccionada by remember { mutableStateOf("") }
                    var nombreSeleccionado by remember { mutableStateOf("") }
                    var user by remember { mutableStateOf("") }
                    var showDialog by remember { mutableStateOf(false) }
                    val context = LocalContext.current
                    var reservaConfirmada by remember { mutableStateOf(false) }

                    Button(
                        onClick = {
                            fechaSeleccionada = reserva.fecha
                            horaSeleccionada = reserva.horario
                            profesorSeleccionado = reserva.profesor
                            salaSeleccionada = reserva.sala
                            nombreSeleccionado = reserva.nombre
                            user = reserva.user

                            showDialog = true
                        },
                        modifier = Modifier.padding(start = 100.dp,end = 100.dp,top=10.dp, bottom = 15.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorBorde,
                            contentColor = Color.White
                        ),
                    ) {
                        Text(text = "Reservar",
                            fontSize = 18.sp)
                    }
                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text("Confirmar envío") },
                            text = { Text("¿Estás seguro de enviar los datos?") },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        val nombreUsuario = SessionManager.getUsername(context)

                                        // Datos para la reserva en clasesReserva
                                        val reservaData = hashMapOf(
                                            "fecha" to fechaSeleccionada,
                                            "horario" to horaSeleccionada,
                                            "sala" to salaSeleccionada,
                                            "profesor" to profesorSeleccionado,
                                            "nombre" to nombreSeleccionado,
                                            "user" to nombreUsuario.toString(),
                                        )

                                        db.collection("clasesReserva")
                                            .add(reservaData)
                                            .addOnSuccessListener { documentReference ->
                                                Log.d("Reserva", "Reserva exitosa. Documento ID: ${documentReference.id}")
                                                mensajeConfirmacion = "Reservado"
                                                reservaConfirmada = true

                                            }
                                            .addOnFailureListener { exception ->
                                                // Error al guardar la reserva
                                                mensajeConfirmacion = "Error al guardar: $exception"
                                                Log.e("Reserva", "Error al guardar la reserva: $exception")
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
                    var puntosOtorgados by remember { mutableStateOf(false) }
                    Button(
                        onClick = {
                            if (reservaConfirmada && !puntosOtorgados) {
                                val nombreUsuario = SessionManager.getUsername(context)

                                // Referencia a la colección "clientes" filtrando por el campo "user"
                                val clientesRef = db.collection("clientes").whereEqualTo("user", nombreUsuario)

                                clientesRef.get()
                                    .addOnSuccessListener { querySnapshot ->
                                        if (!querySnapshot.isEmpty) {
                                            // Debería haber solo un documento correspondiente al usuario
                                            val document = querySnapshot.documents[0]
                                            val puntosActuales = document.getLong("puntos") ?: 0

                                            // Actualizar los puntos en el documento
                                            document.reference.update("puntos", puntosActuales + 5)
                                                .addOnSuccessListener {
                                                    Log.d("Firestore", "Puntos actualizados exitosamente")
                                                    // Establecer el estado de los puntos otorgados como true
                                                    puntosOtorgados = true
                                                }
                                                .addOnFailureListener { exception ->
                                                    Log.e("Firestore", "Error al actualizar los puntos: ${exception.message}")
                                                }
                                        } else {
                                            // Documento no encontrado
                                            Log.e("Firestore", "Documento no encontrado para el usuario $nombreUsuario")
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.e("Firestore", "Error al obtener el documento: ${exception.message}")
                                    }
                            }
                        },
                        modifier = Modifier.padding(start = 100.dp,end = 100.dp, bottom = 15.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (reservaConfirmada && !puntosOtorgados) Color(0xFF4CAF50) else Color.Gray,
                            contentColor = Color.White
                        ),
                    ) {
                        Text(
                            text = "Sumar Puntos",
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}