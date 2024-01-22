package com.example.crudv1.Screens
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun VendedorGuardar(navController:NavHostController) {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Guardar vendedor")
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

            var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue("", TextRange(0, 7)))
            }
        }
    }
    Card(
        modifier = Modifier.padding(top = 65.dp, bottom = 50.dp),
    ) {
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
                var nombre by rememberSaveable { mutableStateOf("") }
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
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = {
                            Text("Username")
                        }
                    )
                }
                var apellido by rememberSaveable { mutableStateOf("") }

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
                        value = apellido,
                        onValueChange = { apellido = it },
                        singleLine = true,
                        label = {
                            Text("Apellidos")
                        }
                    )
                }
                var contraseña by rememberSaveable { mutableStateOf("") }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(12.dp)
                ) {
                    val icon = Icons.Default.Lock
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(44.dp),
                    )
                    OutlinedTextField(
                        value = contraseña,
                        onValueChange = { contraseña = it },
                        singleLine = true,
                        label = {
                            Text("Password")
                        }
                    )
                }

                val icon = Icons.Default.Phone
                var telefono by rememberSaveable { mutableStateOf("") }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(12.dp)
                )
                {

                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(44.dp),
                    )

                    OutlinedTextField(
                        value = telefono,
                        onValueChange = { newValue ->
                            val numericValue = newValue.filter { it.isDigit() }
                            telefono = numericValue.take(9) // Limitar a 9 caracteres
                        },
                        label = {
                            Text("Telefono")
                        }
                    )
                }
                val db = FirebaseFirestore.getInstance()
                val coleccion = "vendedores"

                val dato = hashMapOf(
                    "id" to id.toString(),
                    "nombre" to nombre.toString(),
                    "apellido" to apellido.toString(),
                    "contraseña" to contraseña.toString(),
                    "telefono" to telefono.toString(),
                )
                var mensajeConfirmacion by remember { mutableStateOf("") }

                Button(
                    onClick = {
                        db.collection(coleccion)
                            .document(id)
                            .set(dato)
                            .addOnSuccessListener {
                                mensajeConfirmacion = "Datos modificados correctamente"
                                id = ""
                                nombre = ""
                                apellido = ""
                                contraseña = ""
                                telefono = ""
                            }
                            .addOnFailureListener {
                                mensajeConfirmacion = "No se ha podido guardar"
                                nombre = ""
                                apellido = ""
                                contraseña = ""
                                telefono = ""
                            }
                    },
                    modifier = Modifier.padding(start = 135.dp),  // Agregar padding al inicio
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50),
                        contentColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color.Black)

                )
                {
                    Text(text = "Guardar")
                }
                Spacer(modifier = Modifier.size(5.dp))
                Text(text = mensajeConfirmacion)
            }
        }
    }
}