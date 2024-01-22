package com.example.crudv1.Screens
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClienteConsultar(navController: NavHostController) {
    var clienteEncontrado by remember { mutableStateOf(false) }
    data class Reserva(
        val user: String,
        val nombre:String,
        val apellido: String,
        val contraseña: String,
        val correo: String,
        val telefono: String

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
                    Text("Consultar datos")
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
        ) {

            var datos by remember { mutableStateOf("") }
            var nombre by remember { mutableStateOf("") }
            var contraseña by remember { mutableStateOf("") }
            var apellido by remember { mutableStateOf("") }
            var telefono by remember { mutableStateOf("") }
            var correo by remember { mutableStateOf("") }
            var user by remember { mutableStateOf("") }

            val context = LocalContext.current
            LaunchedEffect(Unit) {
                val db = FirebaseFirestore.getInstance()
                val coleccion = "clientes"
                val nombreUsuario = SessionManager.getUsername(context)
                db.collection(coleccion)
                    .whereEqualTo("user", nombreUsuario)

                    .get()
                    .addOnSuccessListener { resultado ->
                        val reservasUsuario = resultado.documents.map { cliente ->

                            for (cliente in resultado.documents) {
                                user = cliente.getString("user") ?: ""
                                nombre = cliente.getString("nombre") ?: ""
                                apellido = cliente.getString("apellido") ?: ""
                                correo = cliente.getString("correo") ?: ""
                                contraseña = cliente.getString("contraseña") ?: ""
                                telefono = cliente.getString("telefono") ?: ""
                            }

                        }
                        clienteEncontrado = reservasUsuario.isNotEmpty()
                    }
                    .addOnFailureListener {
                        datos = "No ha podido conectar"
                    }
            }
            Card(
                modifier = Modifier
                    .padding(horizontal = 1.dp)
                    .fillMaxWidth(),
                border = BorderStroke(3.dp, Color.Gray)
            ) {
                Column(
                    modifier = Modifier
                        .padding(1.dp)
                ) {
                    if (clienteEncontrado) {

                        Text(
                            modifier = Modifier.padding(start = 130.dp, top = 10.dp),
                            text = "User: $user",
                            fontSize = 20.sp


                        )
                        Text(
                            modifier = Modifier.padding(start = 130.dp, top = 10.dp),
                            text = "Nombre: $nombre",
                            fontSize = 20.sp


                        )
                        Text(
                            modifier = Modifier.padding(start = 130.dp, top = 10.dp),
                            text = "Teléfono: $telefono",
                            fontSize = 20.sp

                        )
                        Text(
                            modifier = Modifier.padding(start = 130.dp, top = 10.dp),
                            text = "Contraseña: $contraseña",
                            fontSize = 20.sp

                        )
                        Text(
                            modifier = Modifier.padding(start = 130.dp, top = 10.dp),
                            text = "Correo: $correo",
                            fontSize = 20.sp

                        )
                        Text(
                            modifier = Modifier.padding(
                                start = 130.dp,
                                top = 10.dp,
                                bottom = 10.dp
                            ),
                            text = "Apellido: $apellido",
                            fontSize = 20.sp

                        )
                    } else {
                        Text(text = datos)
                    }
                }
            }
        }
    }
}