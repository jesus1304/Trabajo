package com.example.crudv1.Screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CambiarReserva(navController: NavHostController) {
    var expanded by remember{ mutableStateOf(false) }
    val options=listOf(
        "Ver reserva",
        "Historial Reservas",
        "Piscina cubierta"

    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Modificar Piscina")
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

                    Button(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                    DropdownMenu(
                        expanded=expanded,
                        onDismissRequest={expanded=false}){
                        options.forEach{ option->
                            DropdownMenuItem(
                                text={Text(text=option)},
                                onClick = { when (option) {
                                    "Ver reserva" -> navController.navigate("reservaPiscina")
                                    "Historial Reservas" -> navController.navigate("MostrarReservas")

                                }
                                    expanded = false
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

        Box(modifier = Modifier.fillMaxWidth()
            .padding(innerPadding)
            .verticalScroll(rememberScrollState()),
        )
        {
            var fecha by rememberSaveable { mutableStateOf("") }
            var horario by rememberSaveable { mutableStateOf("") }
            val mCalendar: Calendar = Calendar.getInstance()
            val hora: Int = mCalendar.get(Calendar.HOUR_OF_DAY)
            val minutos: Int = mCalendar.get(Calendar.MINUTE)
            val anio: Int = mCalendar.get(Calendar.YEAR)
            val mes: Int = mCalendar.get(Calendar.MONTH)
            val dia: Int = mCalendar.get(Calendar.DAY_OF_MONTH)
            val mTimePickerDialog = TimePickerDialog(
                LocalContext.current,
                { _: TimePicker, selectedHour: Int, selectedMinutes: Int ->
                    horario = "$selectedHour:$selectedMinutes"
                },
                hora,
                minutos,
                true
            )
            val mDatePickerDialog = DatePickerDialog(
                LocalContext.current, { _: DatePicker, anio: Int, mes: Int, dia: Int ->
                    fecha = "$dia/${mes + 1}/$anio"
                }, anio, mes, dia
            )

            var mensajeConfirmacion by remember { mutableStateOf("") }
            var piscina by rememberSaveable { mutableStateOf("") }
            var id by rememberSaveable { mutableStateOf("") }
            var showDialog by remember { mutableStateOf(false) }
            Column {


                Column {
                    Text(
                        "\"¡Bienvenido a la página de modificación de reservas! Aquí puedes actualizar tus reservas existentes. Ingresa el ID correspondiente a tu reserva para ver y ajustar los detalles. Una vez que ingreses el ID, podrás visualizar y modificar la información de tu reserva. ¡Estamos aquí para ayudarte a hacer los cambios que necesites en tu reserva de piscina!\"",
                                style = TextStyle(
                                fontSize = 20.sp
                                ),
                        modifier = Modifier
                            .padding(16.dp)
                    )
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
                            label = { Text("Nif") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        val personIcon = Icons.Default.Person
                        Icon(
                            imageVector = personIcon,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(44.dp)
                        )
                        OutlinedTextField(
                            value = piscina,
                            onValueChange = { piscina = it },
                            label = { Text("Elige piscina abierta o cerrada") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
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
                                .clickable { mTimePickerDialog.show() }
                        )
                        OutlinedTextField(
                            value = horario,
                            onValueChange = { horario = it },
                            label = { Text("Select hour") },
                            readOnly = true,
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pis1),
                            contentDescription = "Descripción de la imagen",
                            modifier = Modifier
                                .padding(5.dp)
                                .weight(1f) // Ocupa el espacio disponible igualmente
                                .height(115.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.pis2),
                            contentDescription = "Descripción de la imagen",
                            modifier = Modifier
                                .padding(5.dp)
                                .weight(1f) // Ocupa el espacio disponible igualmente
                                .height(120.dp)
                        )
                    }
                }
                val db = FirebaseFirestore.getInstance()
                val coleccion = "piscina"

                var mensajeConfirmacion by remember { mutableStateOf("") }

                Button(
                    onClick = {
                        if (fecha.isNotEmpty()) {
                            showDialog = true
                        } else {
                            mensajeConfirmacion = "Seleccione una fecha primero"
                        }
                    },
                    modifier = Modifier.padding(start = 155.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(text = "Reservar")
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
                                        "piscina" to piscina.toString(),
                                        "horario" to horario.toString(),

                                        )

                                    db.collection(coleccion)
                                        .document(id)
                                        .set(data)
                                        .addOnSuccessListener {
                                            mensajeConfirmacion = "Datos guardados correctamente"
                                            fecha = ""
                                            piscina = ""
                                            horario = ""
                                            id = ""
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
