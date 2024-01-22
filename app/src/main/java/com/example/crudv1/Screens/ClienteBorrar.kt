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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.navigation.SessionManager
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClienteBorrar(navController: NavHostController) {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Borrar árbitro")
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


            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {

                    val db = FirebaseFirestore.getInstance()
                    val coleccion = "clientes"
                    var mensajeBorrado by remember { mutableStateOf("") }
                    val context = LocalContext.current
                    var id by remember { mutableStateOf("") }
                    var showDialog by remember { mutableStateOf(false) }
                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = {
                                showDialog = false
                            },
                            title = {
                                Text("Confirmar eliminación")
                            },
                            text = {
                                Text("¿Estás seguro de eliminar estos datos?")
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        val nombreUsuario = SessionManager.getUsername(context)

                                        db.collection(coleccion)
                                            .whereEqualTo("user", nombreUsuario)
                                            .get()
                                            .addOnSuccessListener { querySnapshot ->
                                                for (document in querySnapshot) {
                                                    if (document.id == id) {
                                                        db.collection(coleccion)
                                                            .document(document.id)
                                                            .delete()
                                                            .addOnSuccessListener {
                                                                mensajeBorrado = "Datos borrados correctamente"
                                                                showDialog = false // Cierra el diálogo después de borrar
                                                            }
                                                            .addOnFailureListener {
                                                                mensajeBorrado = "No se ha podido borrar"
                                                                id = ""
                                                                showDialog = false // Cierra el diálogo si falla el borrado
                                                            }
                                                        break
                                                    }
                                                }
                                            }
                                            .addOnFailureListener {
                                                mensajeBorrado = "Error al buscar el documento: $it"
                                                showDialog = false // Cierra el diálogo si falla la búsqueda
                                            }
                                    }
                                ) {
                                    Text("Confirmar")
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = {
                                        showDialog = false // Cierra el diálogo si se cancela
                                    }
                                ) {
                                    Text("Cancelar")
                                }
                            }
                        )
                    }

                    Button(
                        onClick = {
                            showDialog = true // Muestra el diálogo al hacer clic en "Borrar"
                        },
                        modifier = Modifier.padding(start = 135.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50),
                            contentColor = Color.White
                        ),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Text(text = "Borrar")
                    }
                }
            }
        }
    }
