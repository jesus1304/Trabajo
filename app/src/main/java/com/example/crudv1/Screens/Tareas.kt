package com.example.crudv1.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Tareas(navController: NavHostController) {
    var tareasPorCompletar by remember { mutableStateOf(generateTareasPorCompletar()) }
    var tareasCompletadas by remember { mutableStateOf(listOf<String>()) }
    var showingCompletedTasks by remember { mutableStateOf(false) }
    var contadorTareas by remember { mutableStateOf(2) } // Iniciando en 2

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            IconButton(onClick = { navController.navigate("MenuPPAL") }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(66.dp))
            Text(
                text = "TAREAS",
                color = Color.White,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier.width(56.dp))
            IconButton(
                onClick = {
                    // Agrega una nueva tarea con el formato "Tarea N"
                    val nuevaTarea = "Tarea $contadorTareas"
                    tareasPorCompletar = tareasPorCompletar + nuevaTarea
                    contadorTareas++ // Incrementa el contador para la siguiente tarea
                }
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    modifier = Modifier.size(36.dp),
                    contentDescription = "Agregar Tarea",
                    tint = Color.White

                )

            }
        }
        Spacer(modifier = Modifier.height(56.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TareasButton(
                text = "Tareas",
                onClick = { showingCompletedTasks = false },
                isClicked = !showingCompletedTasks
            )
            Spacer(modifier = Modifier.width(16.dp))
            TareasButton(
                text = "Tareas Completadas",
                onClick = { showingCompletedTasks = true },
                isClicked = showingCompletedTasks
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (showingCompletedTasks) {
                TareasList(tareas = tareasCompletadas)
            } else {
                TareasList(
                    tareas = tareasPorCompletar,
                    checkedTareas = tareasCompletadas,
                    onCheckedChange = { tarea, isChecked ->
                        if (isChecked) {
                            tareasCompletadas = tareasCompletadas.toMutableList().apply { add(tarea) }
                            tareasPorCompletar = tareasPorCompletar.filter { it != tarea }
                        }
                    },
                    onDeleteTask = { tarea ->
                        tareasPorCompletar = tareasPorCompletar.filter { it != tarea }
                    },
                    onTaskClick = { tarea ->
                        // Navegar a la página deseada con la información de la tarea seleccionada

                        navController.navigate("MenuPPAL")
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(156.dp))
    }
}
@Composable
fun TareasButton(
    text: String,
    onClick: () -> Unit,
    isClicked: Boolean
) {
    val backgroundColor = if (isClicked) Color.White else Color.Gray

    Box(
        modifier = Modifier
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )
    }
    Spacer(modifier = Modifier.height(156.dp))
}


@Composable
fun TareasList(
    tareas: List<String>,
    checkedTareas: List<String> = emptyList(),
    onCheckedChange: ((String, Boolean) -> Unit)? = null,
    onDeleteTask: ((String) -> Unit)? = null,
    onTaskClick: ((String) -> Unit)? = null
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .size(550.dp)
            .background(Color.White) // Color de fondo blanco
    ) {
        items(tareas) { tarea ->
            val isChecked = checkedTareas.contains(tarea)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onTaskClick?.invoke(tarea) } // Agregar navegación al hacer clic
            ) {
                if (onCheckedChange != null) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { newCheckedState ->
                            onCheckedChange.invoke(tarea, newCheckedState)
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Text(
                    text = tarea,
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                )
                IconButton(
                    onClick = { onDeleteTask?.invoke(tarea) },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Eliminar Tarea"
                    )
                }
            }
        }
    }
}


fun generateTareasPorCompletar(): List<String> {
    return listOf("Tarea 1")
}
