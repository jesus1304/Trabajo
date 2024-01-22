package com.example.crudv1.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R
import com.example.crudv1.navigation.SessionManager
import com.example.crudv1.ui.theme.CrudV1Theme
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuInicio(navController: NavHostController) {



    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Perfil")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Inicio") }) {
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

        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.logo2),
                    contentDescription = "Descripción de la imagen",
                    modifier = Modifier
                        .padding(12.dp)
                        .height(70.dp),

                    )

            }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Button(
                        onClick = {
                            navController.navigate("InicioSesion")                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(
                            text = "Cerrar sesión",
                            color = Color.White,
                            fontSize = 20.sp,
                            style = TextStyle.Default
                        )
                    }

                    Button(
                        onClick = { navController.navigate("Puntos") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp),
                        contentPadding = PaddingValues(16.dp),
                        content = {
                            Text(
                                text = "Puntos",
                                color = Color.White,
                                fontSize = 20.sp,
                                style = TextStyle.Default
                            )
                        }
                    )
                    Button(
                        onClick = { navController.navigate("ClienteModificar") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp),
                        contentPadding = PaddingValues(16.dp),
                        content = {
                            Text(
                                text = "Modificar perfil",
                                color = Color.White,
                                fontSize = 20.sp,
                                style = TextStyle.Default
                            )
                        }
                    )
                    val db = FirebaseFirestore.getInstance()
                    val coleccion = "clientes"
                    var mensajeBorrado by remember { mutableStateOf("") }
                    val context = LocalContext.current
                    var showDialog by remember { mutableStateOf(false) }

                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(
                            text = "Eliminar perfil",
                            color = Color.White,
                            fontSize = 20.sp,
                            style = TextStyle.Default
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
                                        val nombreUsuario = SessionManager.getUsername(context)

                                        db.collection(coleccion)
                                            .whereEqualTo("user", nombreUsuario)
                                            .get()
                                            .addOnSuccessListener { querySnapshot ->
                                                for (document in querySnapshot) {
                                                    db.collection(coleccion)
                                                        .document(document.id)
                                                        .delete()
                                                        .addOnSuccessListener {
                                                            mensajeBorrado = "Datos borrados correctamente"
                                                        }
                                                        .addOnFailureListener { exception ->
                                                            mensajeBorrado = "Error al borrar: $exception"
                                                        }
                                                }
                                            }
                                            .addOnFailureListener { exception ->
                                                mensajeBorrado = "Error al buscar el documento: $exception"
                                            }

                                        showDialog = false
                                    },

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
                }
            }
        }
    }
