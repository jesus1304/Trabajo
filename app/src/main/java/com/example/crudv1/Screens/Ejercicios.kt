package com.example.crudv1.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ejercicios(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf(
        "Jugadores",
        "Entrenadores",
        "Árbitros"

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
                                        "Jugadores" -> navController.navigate("MenuInicio")
                                        "Entrenadores" -> navController.navigate("MenuInicio2")
                                        "Árbitros" -> navController.navigate("MenuInicio3")
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
            {

                Text(
                    text = "Si estás comenzando en el mundo del gimnasio y te sientes abrumado por la cantidad de máquinas y opciones disponibles, estás en el lugar adecuado. En esta guía, te presentaré una selección de ejercicios básicos pero fundamentales que te ayudarán a construir una base sólida para tu rutina de entrenamiento. Estos ejercicios son simples, efectivos y versátiles, y te permitirán trabajar diferentes grupos musculares para lograr un progreso constante.",
                    style = TextStyle(
                        fontSize = 21.sp
                    ),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp)
                )

            }
            Text(
                text = "1. Press con mancuernas en banco inclinado",
                style = TextStyle(
                    fontSize = 19.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 5.dp)

            )
            Text(
                text = "Es un ejercicio es muy completo que te permite trabajar los grupos musculares de brazos, hombros y pecho. Lo ideal es realizar 3-4 series de 6 a 10 repeticiones.",
                style = TextStyle(
                    fontSize = 17.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 5.dp)

            )
            Image(
                painter = painterResource(id = R.drawable.ej1),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
            )
            {

            }
            Text(
                text = "2. Dominadas.",
                style = TextStyle(
                    fontSize = 19.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 5.dp)

            )
            Text(
                text = "El músculo braquioradial del antebrazo, el bíceps, hombros y los músculos de la espalda saldrán reforzados gracias a este ejercicio. Intenta realizar 3 o 4 series de 8 a 10 repeticiones.\n" +
                        "\n",
                style = TextStyle(
                    fontSize = 17.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 5.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ej2),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
            )
            {



            }
            Text(
                text = "3. Curl de bíceps con mancuernas en banco ",
                style = TextStyle(
                    fontSize = 19.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 5.dp)

            )
            Text(
                text = "Esta rutina se centra principalmente en los bíceps y músculos del antebrazos. Bastará con realizar 2 series de 8 a 12 repeticiones utilizando mancuernas." +
                        "\n",
                style = TextStyle(
                    fontSize = 17.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 5.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ej3),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
            )
            {



            }
            Text(
                text = "4.Extensión de tríceps con mancuernas en banco ",
                style = TextStyle(
                    fontSize = 19.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 5.dp)

            )
            Text(
                text = "Especialmente indicado para trabajar los trícpes, es aconsejable completar 2 series de 10 a 15 repeticiones elevando las mancuernas por encima de la cabeza desde la espalda.",
                style = TextStyle(
                    fontSize = 17.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 5.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ej4),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
            )
            {

            }
            Text(
                text = "5.Sentadilla con barra",
                style = TextStyle(
                    fontSize = 19.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 5.dp)

            )
            Text(
                text = "En este ejercicio se puede añadir peso gradualmente para progresar y se trabaja específicamente glúteos, cuádriceps e isquitibiales. Lo ideal es realizar de 3 a 5 series de 6 a 8 repeticiones.",
                style = TextStyle(
                    fontSize = 17.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ej5),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Text(
                text = "6.Peso muerto rumano",
                style = TextStyle(
                    fontSize = 19.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 5.dp)

            )
            Text(
                text = "Otro ejercicio que permite regular la intensidad y dificultad con más peso. Es una variante del tradicional deadlift en la que las rodillas se flexionan mínimamente. Puedes completar entre 2 y 4 series de 8 a 10 repeticiones.",
                style = TextStyle(
                    fontSize = 17.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ej6),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Text(
                text = "7.Elevación de talones",
                style = TextStyle(
                    fontSize = 19.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 5.dp)

            )
            Text(
                text = "Puede realizarse tanto de pie como sentado y, en este caso, trabajaremos exclusivamente los gemelos a través de la elevación de los talones. 2-4 series con un mínimo de 6 repeticiones y un máximo de 15.",
                style = TextStyle(
                    fontSize = 17.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.img7),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Text(
                text = "8.Press de banca con barra",
                style = TextStyle(
                    fontSize = 19.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 5.dp)

            )
            Text(
                text = "El press de banca con barra es un ejercicio básico para aumentar los pectorales. Este ejercicio se realiza con el cuerpo acostado sobre un banco, rodillas flexionadas y los pies apoyados en el suelo. Se agarra la barra con ambas manos ligeramente más allá de la altura de los hombros. ",
                style = TextStyle(
                    fontSize = 17.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ej7),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(250.dp)
            )
        }

    }

}
