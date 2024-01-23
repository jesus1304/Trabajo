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
import androidx.compose.material.icons.filled.Email
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
fun Piscina(navController: NavHostController) {
    var optionsExpanded by remember { mutableStateOf(false) }
    var optionsExpanded2 by remember { mutableStateOf(false) }

    val options = listOf(
        "Ver Reservas Piscina Aire Libre",
        "Ver Reservas Piscina Cubierta",
        "Información piscinas"

    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Facturas")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("MenuInicio") }) {
                        Icon(
                            imageVector = Icons.Filled.Person,
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
                                        "Ver Reservas Piscina Aire Libre" -> navController.navigate(
                                            "MostrarReservas"
                                        )

                                        "Ver Reservas Piscina Cubierta" -> navController.navigate(
                                            "MostrarReservas2"
                                        )

                                        "Información piscinas" -> navController.navigate("PiscinaCerrada")

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

        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        )
        {
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
            var selectedHour by remember { mutableStateOf("") }
            var piscina by rememberSaveable { mutableStateOf("") }
            var showDialog by remember { mutableStateOf(false) }
            Column {


                Column {
                    Text(
                        text = "\n" +
                                "¡Disfruta del Verano en Madrid con Nuestras Piscinas!\n" +
                                "\n" +
                                "Sumérgete en la frescura y la diversión con nuestras piscinas en el corazón de Madrid. En nuestra página de reservas, te ofrecemos una amplia gama de opciones para que aproveches al máximo el verano en la ciudad.",
                        style = TextStyle(
                            fontSize = 19.sp
                        ),
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(20.dp)

                    )
                    var direccion by rememberSaveable { mutableStateOf("") }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        val icon = Icons.Default.Email
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(44.dp),
                        )

                        OutlinedTextField(
                            value = direccion,
                            onValueChange = { direccion = it },
                            label = { Text("Direccion") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,

                            )
                    }
                    var Nif by rememberSaveable { mutableStateOf("") }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        val icon = Icons.Default.Email
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(44.dp),
                        )

                        OutlinedTextField(
                            value = Nif,
                            onValueChange = { Nif = it },
                            label = { Text("Nif") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,

                            )
                    }
                    var precio by rememberSaveable { mutableStateOf("") }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        val icon = Icons.Default.Email
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(44.dp),
                        )

                        OutlinedTextField(
                            value = precio,
                            onValueChange = { precio = it },
                            label = { Text("Precio") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,

                            )
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
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pis1),
                            contentDescription = "Descripción de la imagen",
                            modifier = Modifier
                                .padding(5.dp)
                                .weight(1f) // Ocupa el espacio disponible igualmente
                                .height(115.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.pis2),
                            contentDescription = "Descripción de la imagen",
                            modifier = Modifier
                                .padding(5.dp)
                                .weight(1f) // Ocupa el espacio disponible igualmente
                                .height(120.dp)
                        )
                    }
                    val db = FirebaseFirestore.getInstance()
                    var user by remember { mutableStateOf("") }
                    var mensajeConfirmacion by remember { mutableStateOf("") }
                    val context = LocalContext.current
                    var reservaConfirmada by remember { mutableStateOf(false) }

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
                                            "direccion" to direccion.toString(),
                                            "precio" to precio.toString(),
                                            "Nif" to Nif.toString(),
                                            "user" to nombreUsuario.toString(),

                                            )

                                        // Agregar la reserva a la colección "piscina"
                                        db.collection("factura")
                                            .add(reservaData)
                                            .addOnSuccessListener { documentReference ->
                                                Log.d("Reserva", "Reserva exitosa. Documento ID: ${documentReference.id}")

                                                mensajeConfirmacion = "Reservado"
                                                fecha = ""
                                                selectedHour = ""
                                                piscina = ""
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

                }
            }
        }
    }
}
