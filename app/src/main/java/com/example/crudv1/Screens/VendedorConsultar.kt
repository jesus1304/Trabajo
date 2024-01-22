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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VendedorConsultar(navController: NavHostController) {
    var clienteEncontrado by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Consultar vendedor")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("MenuInicio2") }) {
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

            var id by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue("", TextRange(0, 7)))
            }
            OutlinedTextField(
                modifier = Modifier.padding( start = 10.dp),
                value = id,
                onValueChange = { id = it },
                label = { Text("NIF") }
            )

            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                    

Bienvenido a nuestro Centro de Ayuda
En [Nombre de la Empresa], nos preocupamos por brindarte el mejor servicio y atención a tus preguntas e inquietudes. Nuestro equipo está aquí para ayudarte en todo lo que necesites.
Antes de enviar tu consulta, te recomendamos revisar nuestra sección de Preguntas Frecuentes. 
                """.trimIndent(),
            )

            val db = FirebaseFirestore.getInstance()
            val coleccion = "vendedores"
            var nif_busqueda by remember { mutableStateOf("") }
            var datos by remember { mutableStateOf("") }
            var nombre by remember { mutableStateOf("") }
            var contraseña by remember { mutableStateOf("") }
            var apellido by remember { mutableStateOf("") }
            var telefono by remember { mutableStateOf("") }
            var field_busqueda = "id"
            Button(
                onClick = {
                    datos = ""
                    db.collection(coleccion)
                        .whereEqualTo(field_busqueda, id.text)
                        .get()
                        .addOnSuccessListener { resultado ->
                            for (encontrado in resultado) {
                                clienteEncontrado = true
                                nombre = encontrado.getString("nombre") ?: ""
                                telefono = encontrado.getString("telefono") ?: ""
                                contraseña = encontrado.getString("contraseña") ?: ""
                                apellido = encontrado.getString("apellido") ?: ""
                            }
                            if (!clienteEncontrado) {
                                datos = "No existen datos"
                            }
                        }
                        .addOnFailureListener {
                            datos = "No ha podido conectar"
                        }
                },
            )
            {
                Text(text = "Consultar en firebase")
            }

            if (clienteEncontrado) {
                Text(text = "Nombre: $nombre")
                Text(text = "Teléfono: $telefono")
                Text(text = "Contraseña: $contraseña")
                Text(text = "Apellido: $apellido")

            } else {
                Text(text = datos)
            }
        }
    }
}