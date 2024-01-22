package com.example.crudv1.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Gimnasio(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf(
        "Reserva Clases",
        "MostrarReservasGym",
        "Ejercicios basicos",

    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Sad Ocio Deportivo Canal")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Inicio") }) {
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
                        expanded = expanded,
                        onDismissRequest = { expanded = false }) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option) },
                                onClick = {
                                    when (option) {
                                        "Reserva Clases" -> navController.navigate("ReservaClases")
                                        "MostrarReservasGym" -> navController.navigate("MostrarReservasGym")
                                        "Ejercicios basicos" -> navController.navigate("Ejercicios")

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

        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.gim1),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(top=15.dp,start=5.dp)
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
            }
            Row(
                modifier = Modifier.padding(start = 90.dp)
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.reloj),
                    contentDescription = "Descripción de la imagen",
                    modifier = Modifier
                        .height(100.dp)
                )
                Text(
                    text = "55 Min",
                    style = TextStyle(
                        fontSize = 25.sp
                    ),
                    modifier = Modifier
                        .padding(top = 35.dp)

                )
            }
            Text(
                text = "Entrenamiento funcional integrado, basado en progresiones dinámicas de movimiento utilizando el step.\n" +
                        "\n" +
                        "En esta clase mejorarás tu condición física a través del desarrollo de la fuerza y la coordinación, al ritmo de la mejor música",
                style = TextStyle(
                    fontSize = 20.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start=15.dp,end=15.dp)

            )
            Button(
                onClick = { navController.navigate("StepFuncional") },
                modifier = Modifier.padding(start = 100.dp,end = 100.dp,top=10.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White
                ),
                content = {
                    Text(
                        text = "Step Funcional",
                        color = Color.White,
                        fontSize = 20.sp,
                        style = TextStyle.Default
                    )
                }
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.bike),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(top=15.dp,start=5.dp)
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
            }
            Row(
                modifier = Modifier.padding(start = 90.dp)
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.reloj),
                    contentDescription = "Descripción de la imagen",
                    modifier = Modifier
                        .height(100.dp)
                )
                Text(
                    text = "45 Min",
                    style = TextStyle(
                        fontSize = 25.sp
                    ),
                    modifier = Modifier
                        .padding(top = 35.dp)

                )
            }
            Text(
                text = "Bike es una actividad dirigida que consiste en un entrenamiento de resistencia cardiovascular sobre bicicletas estáticas de resistencia variable al ritmo de la mejor música.",
                style = TextStyle(
                    fontSize = 20.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start=15.dp,end=15.dp)

            )
            Button(
                onClick = { navController.navigate("Bike") },
                modifier = Modifier.padding(start = 100.dp,end = 100.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White
                ),
                content = {
                    Text(
                        text = "Bike",
                        color = Color.White,
                        fontSize = 20.sp,
                        style = TextStyle.Default
                    )
                }
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.health),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(top=15.dp,start=5.dp)
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
            }
            Row(
                modifier = Modifier.padding(start = 90.dp)
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.reloj),
                    contentDescription = "Descripción de la imagen",
                    modifier = Modifier
                        .height(100.dp)
                )
                Text(
                    text = "30 Min",
                    style = TextStyle(
                        fontSize = 25.sp
                    ),
                    modifier = Modifier
                        .padding(top = 35.dp)

                )
            }
            Text(
                text = "Es una actividad realizada en el medio acuático destinada a la prevención higiénico-postural y de patologías relacionadas con la columna vertebral.\n" +
                        "\n" +
                        "La mejor opción para los dolores de espalda.",
                style = TextStyle(
                    fontSize = 20.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start=15.dp,end=15.dp)

            )
            Button(
                onClick = { navController.navigate("Health") },
                modifier = Modifier.padding(start = 100.dp,end = 100.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White
                ),
                content = {
                    Text(
                        text = "Aqua Health",
                        color = Color.White,
                        fontSize = 20.sp,
                        style = TextStyle.Default
                    )
                }
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.aqua),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(top=15.dp,start=5.dp)
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
            }
            Row(
                modifier = Modifier.padding(start = 90.dp)
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.reloj),
                    contentDescription = "Descripción de la imagen",
                    modifier = Modifier
                        .height(100.dp)
                )
                Text(
                    text = "45 Min",
                    style = TextStyle(
                        fontSize = 25.sp
                    ),
                    modifier = Modifier
                        .padding(top = 35.dp)

                )
            }
            Text(
                text = "Aqua es una actividad dirigida en el medio acuático que te permitirá mejorar la resistencia muscular y cardiovascular.\n" +
                        "¡Disfruta de un entrenamiento completo dentro del agua!",
                style = TextStyle(
                    fontSize = 20.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start=15.dp,end=15.dp)

            )
            Button(
                onClick = { navController.navigate("Aqua") },
                modifier = Modifier.padding(start = 100.dp,end = 100.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White
                ),
                content = {
                    Text(
                        text = "Aqua",
                        color = Color.White,
                        fontSize = 20.sp,
                        style = TextStyle.Default
                    )
                }
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.fitness1),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(top=15.dp,start=5.dp)
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
            }
            Row(
                modifier = Modifier.padding(start = 90.dp)
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.reloj),
                    contentDescription = "Descripción de la imagen",
                    modifier = Modifier
                        .height(100.dp)
                )
                Text(
                    text = "30 min",
                    style = TextStyle(
                        fontSize = 25.sp
                    ),
                    modifier = Modifier
                        .padding(top = 35.dp)

                )
            }
            Text(
                text = "Únete a una experiencia de entrenamiento único que fusiona movimientos dinámicos, fortalecimiento muscular y ejercicios de cardio. Esta clase te desafía a superar tus límites mientras trabajas en tu resistencia, flexibilidad y coordinación.",
                style = TextStyle(
                    fontSize = 20.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start=15.dp,end=15.dp)

            )
            Button(
                    onClick = { navController.navigate("Fitness") },
                modifier = Modifier.padding(start = 100.dp,end = 100.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White
                ),
                content = {
                    Text(
                        text = "Fitness",
                        color = Color.White,
                        fontSize = 20.sp,
                        style = TextStyle.Default
                    )
                }
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.yoga),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(top=15.dp,start=5.dp)
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
            }
            Row(
                modifier = Modifier.padding(start = 90.dp)
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.reloj),
                    contentDescription = "Descripción de la imagen",
                    modifier = Modifier
                        .height(100.dp)
                )
                Text(
                    text = "55 min",
                    style = TextStyle(
                        fontSize = 25.sp
                    ),
                    modifier = Modifier
                        .padding(top = 35.dp)

                )
            }
            Text(
                text = "Técnica que trabaja la mente, el cuerpo y el espíritu. Es practicable por cualquier persona y edad, favoreciendo rápidamente la mejora de la flexibilidad, el tono muscular y el estado físico en general.",
                style = TextStyle(
                    fontSize = 20.sp
                ),
                modifier = Modifier
                    .padding(start=15.dp,end=15.dp)

            )
            Button(
                onClick = { navController.navigate("Yoga") },
                modifier = Modifier.padding(start = 100.dp,end = 100.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White
                ),
                content = {
                    Text(
                        text = "Yoga",
                        color = Color.White,
                        fontSize = 20.sp,
                        style = TextStyle.Default
                    )
                }
            )
        }
    }
}