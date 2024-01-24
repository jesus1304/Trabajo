package com.example.crudv1.Screens

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R
import com.example.crudv1.navigation.SessionManager
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Proveedores(navController: NavHostController) {
    var optionsExpanded by remember { mutableStateOf(false) }

    val options = listOf(
        "Ver Facturas Usuario",
        "Ver todas las facturas"

    )
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
                    IconButton(onClick = { navController.navigate("MenuInicio") }) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Localized description" ,
                                    tint = Color.White
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
                                        "Ver Facturas Usuario" -> navController.navigate(
                                            "MostrarReservas"
                                        )
                                        "Ver todas las facturas" -> navController.navigate("MostrarFacturas")

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
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(12, 12, 12))

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
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Proveedores",
                        fontSize = 30.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                            .padding(bottom = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    var nombre by rememberSaveable { mutableStateOf("") }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            label = { Text("Name",
                                color = Color.White) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray),
                            leadingIcon = {
                                val icon = Icons.Default.Person
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(44.dp),
                                    tint = Color.White // Puedes ajustar el color del icono según tus preferencias
                                )
                            }
                        )
                    }

                    var apellido by rememberSaveable { mutableStateOf("") }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        OutlinedTextField(
                            value = apellido,
                            onValueChange = { apellido = it },
                            label = { Text("Surnames"
                                , color = Color.White) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray),
                            leadingIcon = {
                                val icon = Icons.Default.Person
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(44.dp),
                                    tint = Color.White // Puedes ajustar el color del icono según tus preferencias
                                )
                            }
                        )
                    }

                    var contraseña by rememberSaveable { mutableStateOf("") }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        OutlinedTextField(
                            value = contraseña,
                            onValueChange = { contraseña = it },
                            label = { Text("Password", color = Color.White) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray),
                            visualTransformation = PasswordVisualTransformation(),
                            leadingIcon = {
                                val icon = Icons.Default.Lock
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(44.dp),
                                    tint = Color.White // Puedes ajustar el color del icono según tus preferencias
                                )
                            }
                        )
                    }

                    var correo by rememberSaveable { mutableStateOf("") }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        OutlinedTextField(
                            value = correo,
                            onValueChange = { correo = it },
                            label = { Text("Email", color = Color.White) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray),
                            leadingIcon = {
                                val icon = Icons.Default.Email
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(44.dp),
                                    tint = Color.White // Puedes ajustar el color del icono según tus preferencias
                                )
                            }
                        )
                    }

                    val icon = Icons.Default.Phone
                    var telefono by rememberSaveable { mutableStateOf("") }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(7.dp)
                    ) {
                        OutlinedTextField(
                            value = telefono,
                            onValueChange = { telefono = it },
                            label = { Text("Telephone", color = Color.White) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray),
                            leadingIcon = {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(44.dp),
                                    tint = Color.White // Puedes ajustar el color del icono según tus preferencias
                                )
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

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
                        modifier = Modifier.padding(start = 10.dp,  top = 12.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Añadir proveedor", fontSize = 18.sp
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
                                            "user" to nombreUsuario.toString(),
                                            )

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
                            modifier = Modifier.padding(top = 5.dp),
                            color = if (mensajeConfirmacion.startsWith("Error")) Color.Red else Color.Green
                        )

                    }
                    Image(
                        painter = painterResource(id = R.drawable.factura2),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)

                    )
                }
            }
        }
    }
}
