package com.example.crudv1.Screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.Retrofit.Cliente
import com.example.crudv1.Retrofit.ClientesViewModel
import com.google.firebase.firestore.FirebaseFirestore
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ClienteGuardar(navController:NavHostController, viewModel: ClientesViewModel){

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(12, 12, 12),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Login") }) {val icon = Icons.Default.AccountCircle
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                },

            )
        },


        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
            .verticalScroll(rememberScrollState()),

            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue("", TextRange(0, 7)))
            }
        }

        Card(
            modifier = Modifier
                .padding(top = 60.dp)
                .fillMaxSize()
                .background(Color(12, 12, 12))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(12, 12, 12))

            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "MerryService",
                            fontSize = 30.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                                .padding(bottom = 16.dp)
                        )
                        var user by rememberSaveable { mutableStateOf("") }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            OutlinedTextField(
                                value = user,
                                onValueChange = { user = it },
                                label = { Text("User",
                                    color = Color.White) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Gray),
                                leadingIcon = {
                                    val icon = Icons.Default.AccountCircle
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = null,
                                        modifier = Modifier.size(44.dp),
                                        tint = Color.White // Puedes ajustar el color del icono según tus preferencias
                                    )
                                }
                            )
                        }

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

                        var contrasena by rememberSaveable { mutableStateOf("") }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            OutlinedTextField(
                                value = contrasena,
                                onValueChange = { contrasena = it },
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

                        val db = FirebaseFirestore.getInstance()
                        val coleccion = "cliente"
                        var showDialog by remember { mutableStateOf(false) }
                        var mensajeConfirmacion by remember { mutableStateOf("") }

                        Button(
                            onClick = {
                                if (nombre.isNotEmpty() && apellido.isNotEmpty() && contrasena.isNotEmpty() &&
                                    telefono.isNotEmpty() && correo.isNotEmpty() && user.isNotEmpty()
                                ) {
                                    val cliente = Cliente(nombre, apellido, contrasena, telefono, correo, user)
                                    viewModel.guardarCliente(cliente)
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
                                text = "Registrarse", fontSize = 18.sp
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
                                                "contrasena" to contrasena,
                                                "telefono" to telefono,
                                                "correo" to correo,
                                                "user" to user,

                                                )

                                            db.collection(coleccion)
                                                .add(data)
                                                .addOnSuccessListener {
                                                    mensajeConfirmacion =
                                                        "Te has registrado"
                                                    nombre = ""
                                                    apellido = ""
                                                    contrasena = ""
                                                    telefono = ""
                                                    correo = ""
                                                    user = ""

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
                    }
                }
            }
        }
    }
}

