package com.example.crudv1.Screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import androidx.compose.ui.platform.LocalContext
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R
import com.example.crudv1.navigation.SessionManager
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Futbol(navController: NavHostController) {
    var optionsExpanded by remember { mutableStateOf(false) }
    var optionsExpanded2 by remember { mutableStateOf(false) }

    val options = listOf(
        "Reservas",
        "Campos"

    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Reservar Piscina")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("MenuInicio") }) {
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
                                        "Reservas" -> navController.navigate(
                                            "ReservasFutbol"
                                        )
                                        "Campos" -> navController.navigate("Campos")

                                    }
                                    optionsExpanded = false
                                }
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {

        },
    ) { innerPadding ->

        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        )
        {
            var expanded by remember { mutableStateOf(false) }
            var fecha by rememberSaveable { mutableStateOf("") }
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
            val horario = (14..22).map { String.format("%02d:00", it) }
            var selectedHour by remember { mutableStateOf("") }
            var campo by rememberSaveable { mutableStateOf("") }
            var showDialog by remember { mutableStateOf(false) }
            Column {


                Column {
                    Text(
                        text = "Descubre la forma más fácil de reservar tu espacio para jugar al fútbol con nuestra aplicación. Realiza reservas rápidas con solo unos toques, explorando una variedad de campos de calidad, desde canchas 5 vs. 5 hasta campos completos. Encuentra horarios flexibles para adaptarse a tus necesidades, recibe notificaciones en tiempo real sobre confirmaciones y ofertas, y realiza pagos seguros directamente desde la palma de tu mano. ¡Haz que cada partido sea posible con nuestra conveniente plataforma de reservas!",
                        style = TextStyle(
                            fontSize = 19.sp
                        ),
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(20.dp)

                    )


                    val opcionesPiscina = listOf("Fútbol 11", "Fútbol 7", "Fútbol 5")

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(top = 25.dp,start=55.dp,end=7.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color.White)
                                .border(1.dp, Color.Gray)
                                .fillMaxWidth()
                                .clickable { optionsExpanded2 = true }
                        ) {
                            Text(
                                text = if (campo.isEmpty()) "Fútbol 11/Fútbol 7/Fútbol 5" else campo,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                            )
                        }
                        DropdownMenu(
                            expanded = optionsExpanded2,
                            onDismissRequest = { optionsExpanded2 = false }
                        ) {
                            opcionesPiscina.forEach { opcion ->
                                Box(
                                    modifier = Modifier
                                        .background(Color.White)
                                        .border(1.dp, Color.Gray)
                                        .fillMaxWidth()
                                        .clickable {
                                            campo = opcion
                                            optionsExpanded2 = false
                                        }
                                ) {
                                    Text(
                                        text = opcion,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }
                        }
                    }


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(7.dp)
                    ) {
                        val dateIcon = Icons.Default.DateRange
                        Icon(
                            imageVector = dateIcon,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier
                                .size(44.dp)
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
                    Row(
                        modifier = Modifier.padding(5.dp),
                    ) {
                        val dateIcon = Icons.Default.DateRange
                        Icon(
                            imageVector = dateIcon,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier
                                .size(44.dp)
                        )
                        Column(
                            modifier = Modifier.padding(3.dp)
                        ) {
                            OutlinedButton(
                                onClick = { expanded = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp),
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                Text(text = if (selectedHour.isNotEmpty()) selectedHour else "Selecciona una hora")
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                for (hour in horario) {
                                    DropdownMenuItem(
                                        text = { Text(text = hour) },
                                        onClick = {
                                            selectedHour = hour
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.campo3),
                            contentDescription = "Descripción de la imagen",
                            modifier = Modifier
                                .padding(15.dp)
                        )

                    }
                    val db = FirebaseFirestore.getInstance()
                    var user by remember { mutableStateOf("") }
                    var mensajeConfirmacion by remember { mutableStateOf("") }
                    val context = LocalContext.current
                    var reservaConfirmada by remember { mutableStateOf(false) }
                    var puntosOtorgados by remember { mutableStateOf(false) }

                    Button(
                        onClick = {
                            if (fecha.isNotEmpty()) {
                                showDialog = true
                            } else {
                                mensajeConfirmacion = "Seleccione una fecha primero"
                            }
                        },
                        modifier = Modifier.padding(start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50),
                            contentColor = Color.White
                        ),
                    ) {
                        Text(
                            text = "Reservar", fontSize = 18.sp
                        )
                    }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = {
                                showDialog = false
                            },
                            title = {
                                Text("Confirmar envío")
                            },
                            text = {
                                Text("¿Estás seguro de enviar los datos?")
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        val nombreUsuario = SessionManager.getUsername(context)

                                        showDialog = false

                                        val reservaData = hashMapOf(
                                            "fecha" to fecha.toString(),
                                            "horario" to selectedHour.toString(),
                                            "campo" to campo.toString(),
                                            "user" to nombreUsuario.toString(),
                                        )

                                        // Agregar la reserva a la colección "piscina"
                                        db.collection("campos")
                                            .add(reservaData)
                                            .addOnSuccessListener { documentReference ->
                                                Log.d("Reserva", "Reserva exitosa. Documento ID: ${documentReference.id}")

                                                mensajeConfirmacion = "Reservado"
                                                fecha = ""
                                                selectedHour = ""
                                                campo = ""
                                                user = ""

                                                showDialog = false
                                                // Establecer el estado de la reserva como confirmada
                                                reservaConfirmada = true
                                            }
                                            .addOnFailureListener { exception ->
                                                // Error al realizar la reserva
                                                val errorMessage = exception.message ?: "Error desconocido al realizar la reserva"
                                                mensajeConfirmacion = "Error al realizar la reserva: $errorMessage"
                                                showDialog = false
                                                Log.e("Reserva", "Error al realizar la reserva: $errorMessage")
                                            }
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
                            modifier = Modifier.padding(top = 16.dp),
                            color = if (mensajeConfirmacion.startsWith("Error")) Color.Red else Color.Green
                        )

                    }
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
                                                        document.reference.update("puntos", puntosActuales + 10)
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
                            modifier = Modifier.padding(start = 25.dp, end = 25.dp)
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (reservaConfirmada && !puntosOtorgados) Color(0xFF4CAF50) else Color.Gray,
                                contentColor = Color.White
                            ),
                        ) {
                            Text(
                                text = "Sumar 10 Puntos",
                                fontSize = 18.sp
                        )
                    }

                }
            }
        }
    }
}
