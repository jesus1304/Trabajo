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
        "Lista proveedores",

    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(12, 12, 12),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Proveedores", color = Color.White)
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
                                        "Lista proveedores" -> navController.navigate(
                                            "MostrarProveedores"
                                        )

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
            Column {



                Column {
                    Spacer(modifier = Modifier.height(15.dp))


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
                    val coleccion = "proveedor"
                    var showDialog by remember { mutableStateOf(false) }
                    var mensajeConfirmacion by remember { mutableStateOf("") }

                    Button(
                        onClick = {
                            if (nombre.isNotEmpty() && apellido.isNotEmpty()  &&
                                telefono.isNotEmpty() && correo.isNotEmpty()
                            ) {
                                showDialog = true
                            } else {
                                mensajeConfirmacion =
                                    "Por favor, completa todos los campos" // Mensaje de error si falta algún campo
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
                                        val data = hashMapOf(
                                            "nombre" to nombre,
                                            "apellido" to apellido,
                                            "telefono" to telefono,
                                            "correo" to correo,

                                            )

                                        db.collection(coleccion)
                                            .add(data)
                                            .addOnSuccessListener {
                                                mensajeConfirmacion =
                                                    "Has añadido un proveedor"
                                                nombre = ""
                                                apellido = ""
                                                telefono = ""
                                                correo = ""

                                                showDialog = false
                                            }
                                            .addOnFailureListener { exception ->
                                                mensajeConfirmacion =
                                                    "Error al guardar: $exception"
                                                showDialog = false
                                            }
                                    }
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
                            modifier = Modifier.padding(top = 15.dp),
                            color = if (mensajeConfirmacion.startsWith("Error")) Color.Red else Color.Green
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.proveedor),
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
