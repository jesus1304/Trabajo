package com.example.crudv1.Screens
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
fun ReservaPiscina(navController: NavHostController) {
    var clienteEncontrado by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Ver reserva")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Piscina") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {

                    IconButton(onClick = { navController.navigate("InicioSesion") }) {
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

            var id by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(TextFieldValue("", TextRange(0, 7)))
            }
            OutlinedTextField(
                modifier = Modifier.padding(start = 10.dp),
                value = id,
                onValueChange = { id = it },
                label = { Text("NIF") }
            )

            Text(
                text="¡Reserva tu día en la piscina hoy mismo!\n" +
                        "\n" +
                        "¿Estás buscando disfrutar de un día relajante en nuestra piscina? ¡Estás en el lugar indicado! Nuestra piscina ofrece un espacio ideal para relajarte, divertirte y disfrutar del sol.",
                style = TextStyle(
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .padding(16.dp)
            )
            val db = FirebaseFirestore.getInstance()
            val coleccion = "piscina"
            var datos by remember { mutableStateOf("") }
            var fecha by remember { mutableStateOf("") }
            var piscina by remember { mutableStateOf("") }
            var horario by remember { mutableStateOf("") }

            var field_busqueda = "id"


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp),
                contentPadding = PaddingValues(7.dp),
                onClick = {

                    datos = ""
                    db.collection(coleccion)
                        .whereEqualTo(field_busqueda, id.text)
                        .get()
                        .addOnSuccessListener { resultado ->
                            for (encontrado in resultado) {
                                clienteEncontrado = true
                                fecha = encontrado.getString("fecha") ?: ""
                                piscina = encontrado.getString("piscina") ?: ""
                                horario = encontrado.getString("horario") ?: ""

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
                Text(text = "Ver  reserva",
                    color = Color.White, // Establece el color del texto como blanco
                    fontSize = 20.sp, // Establece el tamaño de texto como 20sp (alta)
                    style = TextStyle.Default)
            }
            Button(
                onClick = { navController.navigate("CambiarReserva") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp),
                contentPadding = PaddingValues(7.dp),
                content = {
                    Text(
                        text = "Cambiar reserva",
                        color = Color.White, // Establece el color del texto como blanco
                        fontSize = 20.sp, // Establece el tamaño de texto como 20sp (alta)
                        style = TextStyle.Default
                    )
                }
            )
            Button(
                onClick = { navController.navigate("BorrarReserva") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp),
                contentPadding = PaddingValues(7.dp),
                content = {
                    Text(
                        text = "Borrar reserva",
                        color = Color.White, // Establece el color del texto como blanco
                        fontSize = 20.sp, // Establece el tamaño de texto como 20sp (alta)
                        style = TextStyle.Default
                    )
                }
            )
            Card(
                modifier = Modifier
                    .padding(start = 10.dp, top = 20.dp)
                    .fillMaxWidth(),
                border = BorderStroke(3.dp, Color.Gray)
            ) {
                Row(
                    modifier = Modifier
                        .padding(1.dp)
                ) {
                    if (clienteEncontrado) {

                        Text(
                            modifier = Modifier.padding(start = 30.dp, top = 10.dp),
                            text = "Piscina: $piscina",
                            fontSize = 18.sp
                        )

                        Text(
                            modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                            text = "Fecha: $fecha",
                            fontSize = 18.sp

                        )

                            Text(
                                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                                text = "Hora: $horario",
                                fontSize = 18.sp

                            )
                        }
                    }
            }
        }
    }
}
