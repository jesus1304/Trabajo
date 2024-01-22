package com.example.crudv1.Screens
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Clases(navController:NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    var optionsExpanded by remember { mutableStateOf(false) }
    val options = listOf(
        "Ver reserva",
        "Historial Reservas",

        )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Reservar Step Funcional")
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

                    Button(onClick = { optionsExpanded = !optionsExpanded }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                    DropdownMenu(
                        expanded = optionsExpanded,
                        onDismissRequest = { optionsExpanded = false }) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option) },
                                onClick = {
                                    when (option) {
                                        "Ver reserva" -> navController.navigate("reservaStep")
                                        "Historial Reservas" -> navController.navigate("MostrarReservas")

                                    }
                                    optionsExpanded = false
                                }
                            )
                        }
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

        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        )
        {
            var fecha by rememberSaveable { mutableStateOf("") }
            val mCalendar: Calendar = Calendar.getInstance()
            val anio: Int = mCalendar.get(Calendar.YEAR)
            val mes: Int = mCalendar.get(Calendar.MONTH)
            val dia: Int = mCalendar.get(Calendar.DAY_OF_MONTH)
            var nombre by rememberSaveable { mutableStateOf("") }
            var profesor by rememberSaveable { mutableStateOf("") }
            var sala by rememberSaveable { mutableStateOf("") }

            val mDatePickerDialog = DatePickerDialog(
                LocalContext.current, { _: DatePicker, anio: Int, mes: Int, dia: Int ->
                    fecha = "$dia/${mes + 1}/$anio"
                }, anio, mes, dia
            )

            var mensajeConfirmacion by remember { mutableStateOf("") }
            var id by rememberSaveable { mutableStateOf("") }
            var showDialog by remember { mutableStateOf(false) }
            val horario = (8..21).map { String.format("%02d:00", it) }
            var selectedHour by remember { mutableStateOf("") }

            Column {

                Column {


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(top = 30.dp)
                    ) {
                        val personIcon = Icons.Default.Person
                        Icon(
                            imageVector = personIcon,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(44.dp)
                        )
                        OutlinedTextField(
                            value = id,
                            onValueChange = { id = it },
                            label = { Text("Número Clase") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(7.dp)
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
                            label = { Text("Nombre") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,

                            )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(7.dp)
                    ) {
                        val icon = Icons.Default.Person
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(44.dp),
                        )
                        OutlinedTextField(
                            value = profesor,
                            onValueChange = { profesor = it },
                            label = { Text("Profesor") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,

                            )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(7.dp)
                    ) {
                        val icon = Icons.Default.Person
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(44.dp),
                        )
                        OutlinedTextField(
                            value = sala,
                            onValueChange = { sala = it },
                            label = { Text("Sala") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,

                            )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(7.dp)
                    ) {
                        val dateIcon = Icons.Default.DateRange
                        Icon(
                            imageVector = dateIcon,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier
                                .size(44.dp)
                                .clickable { mDatePickerDialog.show() }
                                .align(Alignment.CenterVertically)
                        )
                        OutlinedTextField(
                            value = fecha,
                            onValueChange = { fecha = it },
                            label = { Text("Select date") },
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                    Row(
                        modifier = Modifier.padding(5.dp),
                    ) {
                        val dateIcon = Icons.Default.DateRange
                        Icon(
                            imageVector = dateIcon,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier
                                .size(44.dp)
                        )
                        Column(
                            modifier = Modifier.padding(3.dp)
                        ) {
                            OutlinedButton(
                                onClick = { expanded = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp),
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                Text(text = if (selectedHour.isNotEmpty()) selectedHour else "Selecciona una hora")
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                for (hour in horario) {
                                    DropdownMenuItem(
                                        text = { Text(text = hour) },
                                        onClick = {
                                            selectedHour = hour
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Image(
                        painter = painterResource(id = R.drawable.step),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(10.dp)
                            .height(250.dp)
                    )
                }
                val db = FirebaseFirestore.getInstance()
                val coleccion = "clases"

                var mensajeConfirmacion by remember { mutableStateOf("") }

                Button(
                    onClick = {
                        if (fecha.isNotEmpty()) {
                            showDialog = true
                        } else {
                            mensajeConfirmacion = "Seleccione una fecha primero"
                        }
                    },
                    modifier = Modifier.padding(start = 100.dp,end = 100.dp,top=10.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50),
                        contentColor = Color.White
                    ),
                ) {
                    Text(text = "Reservar",
                        fontSize = 18.sp)
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
                                        "horario" to selectedHour.toString(),
                                        "profesor" to profesor.toString(),
                                        "sala" to sala.toString(),
                                        "nombre" to nombre.toString(),


                                        )

                                    db.collection(coleccion)
                                        .document(id)
                                        .set(data)
                                        .addOnSuccessListener {
                                            mensajeConfirmacion = "Datos guardados correctamente"
                                            fecha = ""
                                            selectedHour=""
                                            id = ""
                                            profesor=""
                                            sala=""
                                            nombre=""
                                            showDialog = false
                                        }
                                        .addOnFailureListener { exception ->
                                            mensajeConfirmacion = "Error al guardar: $exception"
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

                // Mostrar mensaje de confirmación o error
                if (mensajeConfirmacion.isNotEmpty()) {
                    Text(
                        text = mensajeConfirmacion,
                        modifier = Modifier.padding(top = 16.dp),
                        color = if (mensajeConfirmacion.startsWith("Error")) Color.Red else Color.Green
                    )
                }
            }
        }
    }
}

