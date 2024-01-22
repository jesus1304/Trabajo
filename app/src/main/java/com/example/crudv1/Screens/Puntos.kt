package com.example.crudv1.Screens

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
fun Puntos(navController: NavHostController) {


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Premios")
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

        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            var datos by remember { mutableStateOf("") }
            var clienteEncontrado by remember { mutableStateOf(false) }
            var puntos by remember { mutableStateOf("") }

            val db = FirebaseFirestore.getInstance()
            val coleccion = "clientes"

            // Configurar un listener en tiempo real
            LaunchedEffect(Unit) {
                val listener = db.collection(coleccion)
                    .addSnapshotListener { snapshot, error ->
                        if (error != null) {
                            datos = "No ha podido conectar"
                            return@addSnapshotListener
                        }

                        if (snapshot != null && !snapshot.isEmpty) {
                            val cliente = snapshot.documents[0]
                            puntos = cliente.getLong("puntos")?.toString() ?: ""
                        }
                    }

            }

            Surface(
                color = Color.Green,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Puntos: $puntos",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column() {

                    Text(
                        text = "Toalla",
                        style = TextStyle(
                            fontSize = 23.sp
                        ),
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(15.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.toalla),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(5.dp)
                            .height(150.dp)
                    )
                }
                Column() {
                    Text(
                        text = "150 puntos",
                        style = TextStyle(
                            fontSize = 22.sp
                        ),
                        textAlign = TextAlign.Justify,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(22.dp)
                    )
                    val db = FirebaseFirestore.getInstance()
                    val context = LocalContext.current

                    var canDescontar by remember { mutableStateOf(true) }

                    Button(
                        onClick = {
                            if (canDescontar) {
                                val nombreUsuario = SessionManager.getUsername(context)

                                val clientesRef =
                                    db.collection("clientes").whereEqualTo("user", nombreUsuario)

                                clientesRef.get()
                                    .addOnSuccessListener { querySnapshot ->
                                        if (!querySnapshot.isEmpty) {
                                            val document = querySnapshot.documents[0]
                                            val puntosActuales = document.getLong("puntos") ?: 0

                                            if (puntosActuales >= 150) {
                                                document.reference.update(
                                                    "puntos",
                                                    puntosActuales - 150
                                                )
                                                    .addOnSuccessListener {
                                                        Log.d(
                                                            "Firestore",
                                                            "Puntos actualizados exitosamente"
                                                        )

                                                        canDescontar = false
                                                    }
                                                    .addOnFailureListener { exception ->
                                                        Log.e(
                                                            "Firestore",
                                                            "Error al actualizar los puntos: ${exception.message}"
                                                        )
                                                    }
                                            } else {
                                                Log.e(
                                                    "Firestore",
                                                    "No hay suficientes puntos para restar"
                                                )
                                            }
                                        } else {
                                            Log.e(
                                                "Firestore",
                                                "Documento no encontrado para el usuario $nombreUsuario"
                                            )
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.e(
                                            "Firestore",
                                            "Error al obtener el documento: ${exception.message}"
                                        )
                                    }
                            }
                        },
                        modifier = Modifier.padding(start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (canDescontar) Color(0xFF4CAF50) else Color.Gray,
                            contentColor = Color.White
                        ),
                    ) {
                        Text(
                            text = "Canjear",
                            fontSize = 18.sp
                        )
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column() {

                    Text(
                        text = "Colonia",
                        style = TextStyle(
                            fontSize = 19.sp
                        ),
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(20.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.colonia),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(5.dp)
                            .height(120.dp)
                    )
                }
                Column() {
                    Text(
                        text = "175 puntos",
                        style = TextStyle(
                            fontSize = 22.sp
                        ),
                        textAlign = TextAlign.Justify,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(22.dp)
                    )
                    val db = FirebaseFirestore.getInstance()
                    val context = LocalContext.current

                    var canDescontar by remember { mutableStateOf(true) }

                    Button(
                        onClick = {
                            if (canDescontar) {
                                val nombreUsuario = SessionManager.getUsername(context)

                                // Referencia a la colección "clientes" filtrando por el campo "user"
                                val clientesRef =
                                    db.collection("clientes").whereEqualTo("user", nombreUsuario)

                                clientesRef.get()
                                    .addOnSuccessListener { querySnapshot ->
                                        if (!querySnapshot.isEmpty) {
                                            // Debería haber solo un documento correspondiente al usuario
                                            val document = querySnapshot.documents[0]
                                            val puntosActuales = document.getLong("puntos") ?: 0

                                            // Verificar que haya suficientes puntos antes de restar
                                            if (puntosActuales >= 175) {
                                                // Actualizar los puntos en el documento
                                                document.reference.update(
                                                    "puntos",
                                                    puntosActuales - 175
                                                )
                                                    .addOnSuccessListener {
                                                        Log.d(
                                                            "Firestore",
                                                            "Puntos actualizados exitosamente"
                                                        )

                                                        // Después de descontar los puntos, establecer canDescontar a false
                                                        canDescontar = false
                                                    }
                                                    .addOnFailureListener { exception ->
                                                        Log.e(
                                                            "Firestore",
                                                            "Error al actualizar los puntos: ${exception.message}"
                                                        )
                                                    }
                                            } else {
                                                // No hay suficientes puntos para restar
                                                Log.e(
                                                    "Firestore",
                                                    "No hay suficientes puntos para restar"
                                                )
                                            }
                                        } else {
                                            // Documento no encontrado
                                            Log.e(
                                                "Firestore",
                                                "Documento no encontrado para el usuario $nombreUsuario"
                                            )
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.e(
                                            "Firestore",
                                            "Error al obtener el documento: ${exception.message}"
                                        )
                                    }
                            }
                        },
                        modifier = Modifier.padding(start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (canDescontar) Color(0xFF4CAF50) else Color.Gray,
                            contentColor = Color.White
                        ),
                    ) {
                        Text(
                            text = "Canjear",
                            fontSize = 18.sp
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column() {

                    Text(
                        text = "Gorro piscina",
                        style = TextStyle(
                            fontSize = 19.sp
                        ),
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(20.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.gorro),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(5.dp)
                            .height(120.dp)
                    )
                }
                Column() {
                    Text(
                        text = "200 puntos",
                        style = TextStyle(
                            fontSize = 22.sp
                        ),
                        textAlign = TextAlign.Justify,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(22.dp)
                    )
                    val db = FirebaseFirestore.getInstance()
                    val context = LocalContext.current

                    var canDescontar by remember { mutableStateOf(true) }

                    Button(
                        onClick = {
                            if (canDescontar) {
                                val nombreUsuario = SessionManager.getUsername(context)

                                val clientesRef =
                                    db.collection("clientes").whereEqualTo("user", nombreUsuario)

                                clientesRef.get()
                                    .addOnSuccessListener { querySnapshot ->
                                        if (!querySnapshot.isEmpty) {
                                            // Debería haber solo un documento correspondiente al usuario
                                            val document = querySnapshot.documents[0]
                                            val puntosActuales = document.getLong("puntos") ?: 0

                                            // Verificar que haya suficientes puntos antes de restar
                                            if (puntosActuales >= 200) {
                                                // Actualizar los puntos en el documento
                                                document.reference.update(
                                                    "puntos",
                                                    puntosActuales - 200
                                                )
                                                    .addOnSuccessListener {
                                                        Log.d(
                                                            "Firestore",
                                                            "Puntos actualizados exitosamente"
                                                        )

                                                        // Después de descontar los puntos, establecer canDescontar a false
                                                        canDescontar = false
                                                    }
                                                    .addOnFailureListener { exception ->
                                                        Log.e(
                                                            "Firestore",
                                                            "Error al actualizar los puntos: ${exception.message}"
                                                        )
                                                    }
                                            } else {
                                                // No hay suficientes puntos para restar
                                                Log.e(
                                                    "Firestore",
                                                    "No hay suficientes puntos para restar"
                                                )
                                            }
                                        } else {
                                            // Documento no encontrado
                                            Log.e(
                                                "Firestore",
                                                "Documento no encontrado para el usuario $nombreUsuario"
                                            )
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.e(
                                            "Firestore",
                                            "Error al obtener el documento: ${exception.message}"
                                        )
                                    }
                            }
                        },
                        modifier = Modifier.padding(start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (canDescontar) Color(0xFF4CAF50) else Color.Gray,
                            contentColor = Color.White
                        ),
                    ) {
                        Text(
                            text = "Canjear",
                            fontSize = 18.sp
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column() {

                    Text(
                        text = "Mochila",
                        style = TextStyle(
                            fontSize = 19.sp
                        ),
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(20.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.mochila),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(5.dp)
                            .height(120.dp)
                    )
                }
                Column() {
                    Text(
                        text = "250 puntos",
                        style = TextStyle(
                            fontSize = 22.sp
                        ),
                        textAlign = TextAlign.Justify,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(22.dp)
                    )
                    val db = FirebaseFirestore.getInstance()
                    val context = LocalContext.current

                    var canDescontar by remember { mutableStateOf(true) }

                    Button(
                        onClick = {
                            if (canDescontar) {
                                val nombreUsuario = SessionManager.getUsername(context)

                                // Referencia a la colección "clientes" filtrando por el campo "user"
                                val clientesRef =
                                    db.collection("clientes").whereEqualTo("user", nombreUsuario)

                                clientesRef.get()
                                    .addOnSuccessListener { querySnapshot ->
                                        if (!querySnapshot.isEmpty) {
                                            // Debería haber solo un documento correspondiente al usuario
                                            val document = querySnapshot.documents[0]
                                            val puntosActuales = document.getLong("puntos") ?: 0

                                            // Verificar que haya suficientes puntos antes de restar
                                            if (puntosActuales >= 250) {
                                                // Actualizar los puntos en el documento
                                                document.reference.update(
                                                    "puntos",
                                                    puntosActuales - 250
                                                )
                                                    .addOnSuccessListener {
                                                        Log.d(
                                                            "Firestore",
                                                            "Puntos actualizados exitosamente"
                                                        )

                                                        // Después de descontar los puntos, establecer canDescontar a false
                                                        canDescontar = false
                                                    }
                                                    .addOnFailureListener { exception ->
                                                        Log.e(
                                                            "Firestore",
                                                            "Error al actualizar los puntos: ${exception.message}"
                                                        )
                                                    }
                                            } else {
                                                // No hay suficientes puntos para restar
                                                Log.e(
                                                    "Firestore",
                                                    "No hay suficientes puntos para restar"
                                                )
                                            }
                                        } else {
                                            // Documento no encontrado
                                            Log.e(
                                                "Firestore",
                                                "Documento no encontrado para el usuario $nombreUsuario"
                                            )
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.e(
                                            "Firestore",
                                            "Error al obtener el documento: ${exception.message}"
                                        )
                                    }
                            }
                        },
                        modifier = Modifier.padding(start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (canDescontar) Color(0xFF4CAF50) else Color.Gray,
                            contentColor = Color.White
                        ),
                    ) {
                        Text(
                            text = "Canjear",
                            fontSize = 18.sp
                        )
                    }
                }

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column() {

                    Text(
                        text = "Mochila",
                        style = TextStyle(
                            fontSize = 19.sp
                        ),
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(20.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.mochila),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(5.dp)
                            .height(120.dp)
                    )
                }
                Column() {
                    Text(
                        text = "300 puntos",
                        style = TextStyle(
                            fontSize = 19.sp
                        ),
                        textAlign = TextAlign.Justify,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(22.dp)
                    )
                    val db = FirebaseFirestore.getInstance()
                    val context = LocalContext.current

                    var canDescontar by remember { mutableStateOf(true) }

                    Button(
                        onClick = {
                            if (canDescontar) {
                                val nombreUsuario = SessionManager.getUsername(context)

                                val clientesRef =
                                    db.collection("clientes").whereEqualTo("user", nombreUsuario)

                                clientesRef.get()
                                    .addOnSuccessListener { querySnapshot ->
                                        if (!querySnapshot.isEmpty) {
                                            val document = querySnapshot.documents[0]
                                            val puntosActuales = document.getLong("puntos") ?: 0

                                            if (puntosActuales >=300) {
                                                document.reference.update(
                                                    "puntos",
                                                    puntosActuales - 300
                                                )
                                                    .addOnSuccessListener {
                                                        Log.d(
                                                            "Firestore",
                                                            "Puntos actualizados exitosamente"
                                                        )

                                                        canDescontar = false
                                                    }
                                                    .addOnFailureListener { exception ->
                                                        Log.e(
                                                            "Firestore",
                                                            "Error al actualizar los puntos: ${exception.message}"
                                                        )
                                                    }
                                            } else {

                                                Log.e(
                                                    "Firestore",
                                                    "No hay suficientes puntos para restar"
                                                )
                                            }
                                        } else {
                                            // Documento no encontrado
                                            Log.e(
                                                "Firestore",
                                                "Documento no encontrado para el usuario $nombreUsuario"
                                            )
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.e(
                                            "Firestore",
                                            "Error al obtener el documento: ${exception.message}"
                                        )
                                    }
                            }
                        },
                        modifier = Modifier.padding(start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (canDescontar) Color(0xFF4CAF50) else Color.Gray,
                            contentColor = Color.White
                        ),
                    ) {
                        Text(
                            text = "Canjear",
                            fontSize = 18.sp
                        )
                    }
                }
            }

        }
    }
}



