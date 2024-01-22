package com.example.crudv1.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BorrarReserva(navController: NavHostController) {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Borrar reserva")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("reservaPiscina") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {

                    IconButton(onClick = { navController.navigate("MenuInicio") }) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {

            }
        },
        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue("", TextRange(0, 7)))
            }
        }
    }

    Card(
        modifier = Modifier.padding(top = 65.dp, bottom = 50.dp),
    ) {
        Text(
            text = "\"Al presionar 'Borrar', se eliminará la reserva asociada a esta acción.\"",
            style = TextStyle(
                fontSize = 20.sp
            ),
            modifier = Modifier
                .padding(16.dp)
        )
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                var id by rememberSaveable { mutableStateOf("") }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Borrar reserva",
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        val icon = Icons.Default.Person
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(44.dp),
                        )
                        OutlinedTextField(
                            value = id,
                            onValueChange = { newValue ->
                                val numericValue = newValue.filter { it.isDigit() }
                                id = numericValue.take(9) // Limitar a 9 caracteres
                            },
                            label = {
                                Text("Nif")
                            }
                        )
                    }
                    val db = FirebaseFirestore.getInstance()
                    val coleccion = "piscina"
                    var mensajeBorrado by remember { mutableStateOf("") }
                    var fecha by remember { mutableStateOf("") }
                    var piscina by remember { mutableStateOf("") }
                    var horario by remember { mutableStateOf("") }
                    var showDialog by remember { mutableStateOf(false) }

                    var mensajeConfirmacion by remember { mutableStateOf("") }

                    Button(
                        onClick = {
                            if (id.isNotEmpty()) {
                                showDialog = true
                            } else {
                                mensajeConfirmacion = "Ingresa primero el id"
                            }
                        },
                        modifier = Modifier.padding(start = 155.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White
                        ),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Text(text = "Borrar")
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
                                            "id" to id.toString(),
                                            "fecha" to fecha.toString(),
                                            "piscina" to piscina.toString(),
                                            "horario" to horario.toString(),

                                            )
                                        if (id.isNotBlank()) {

                                            db.collection(coleccion)
                                                .document(id)
                                                .delete()
                                                .addOnSuccessListener {
                                                    mensajeBorrado = "Datos borrados correctamente"
                                                    id = ""
                                                    showDialog = false

                                                }
                                                .addOnFailureListener {
                                                    mensajeBorrado = "No se ha podido borrar"
                                                    id = ""
                                                    showDialog = false

                                                }
                                        }
                                    },
                                            modifier = Modifier.padding(start = 130.dp),  // Agregar padding al inicio
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF4CAF50),
                                        contentColor = Color.White
                                    ),
                                )
                                {
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

                    if (mensajeBorrado.isNotEmpty()) {
                        Text(
                            text = mensajeBorrado,
                            modifier = Modifier.padding(top = 16.dp),
                            color = if (mensajeBorrado.startsWith("Error")) Color.Red else Color.Green
                        )
                    }
                }
            }
        }
        }
    }