package com.example.crudv1.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClienteInforme(navController: NavHostController) {
    var clienteEncontrado by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Consultar árbitro")
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
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()), // Agrega el desplazamiento vertical aquí
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ){
            val db = FirebaseFirestore.getInstance()
            val coleccion = "clientes"
            var datos by remember { mutableStateOf("") }
            var clientesList by remember { mutableStateOf(emptyList<DocumentSnapshot>()) }
            Button(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp
                    ),
                onClick = {
                    clientesList = emptyList()
                    db.collection(coleccion)
                        .get()
                        .addOnSuccessListener { resultado ->
                            clientesList = resultado.documents
                            clienteEncontrado = clientesList.isNotEmpty()
                        }
                        .addOnFailureListener {
                            datos = "No ha podido conectar"
                        }
                },
            ) {
                Text(text = "Consultar todos los clientes en Firebase")
            }
            for (cliente in clientesList) {
                val id = cliente.getString("id") ?: ""
                val nombre = cliente.getString("nombre") ?: ""
                val telefono = cliente.getString("telefono") ?: ""
                val contraseña = cliente.getString("contraseña") ?: ""
                val apellido = cliente.getString("apellido") ?: ""

                Card(
                    modifier = Modifier
                        .padding(horizontal = 1.dp)
                        .fillMaxWidth(),
                    border = BorderStroke(3.dp, Color.Gray)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(1.dp)
                            .fillMaxWidth(),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 16.dp),
                                text = "Id: $id",
                                fontSize = 22.sp

                            )
                            Text(
                                modifier = Modifier.padding(start = 16.dp),
                                text = "Nombre: $nombre",
                                fontSize = 22.sp

                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 16.dp),
                                text = "Teléfono: $telefono",
                                fontSize = 22.sp

                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 16.dp),
                                text = "Contraseña: $contraseña",
                                fontSize = 22.sp

                            )
                            Text(
                                modifier = Modifier.padding(start = 16.dp),
                                text = "Apellido: $apellido",
                                fontSize = 22.sp

                            )
                        }
                    }
                }
            }
        }
    }
}