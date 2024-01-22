package com.example.crudv1.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
fun PiscinaCerrada(navController: NavHostController) {

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
                    IconButton(onClick = { navController.navigate("Piscina") }) {
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
        ){
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
                        painter = painterResource(id = R.drawable.piscerrada),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
            }
                    Text(
                        text = "¡Bienvenidos a nuestra Piscina Pública!\n" +
                                "\n" +
                                "¡Sumérgete en la diversión en nuestra moderna y acogedora piscina pública! En Sad Ocio y Deporte Canal, nos enorgullecemos de ofrecer un espacio acuático excepcional que combina entretenimiento, ejercicio y relajación para toda la familia.\n" +
                                "\n" +
                                "Instalaciones y servicios:\n" +
                                "\n" +
                                "-Piscinas Variadas: Disfruta de nuestras piscinas diseñadas para satisfacer todas las edades y preferencias. Desde áreas de chapoteo para los más pequeños hasta piscinas recreativas y de entrenamiento para adultos.\n" +
                                "\n" +
                                "-Zonas de Descanso: Relájate en nuestras cómodas áreas de descanso y toma el sol mientras disfrutas de un ambiente agradable y ameno.\n" +
                                "\n" +
                                "-Programas de Acondicionamiento Físico: Ofrecemos clases de natación para todas las edades y niveles, así como sesiones especiales de ejercicios acuáticos para mantenerse en forma de una manera divertida y refrescante.\n" +
                                "\n" +
                                "-Cafetería y Áreas de Picnic: Recarga energías con nuestras opciones gastronómicas y disfruta de deliciosos snacks y bebidas en nuestras áreas de picnic.\n" +
                                "\n" +
                                "Horario: 9:00-21:00\n" ,
                        style = TextStyle(
                            fontSize = 18.sp
                        ),
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(22.dp)

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
                        painter = painterResource(id = R.drawable.sauna),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
            }
            Text(
                text = "\n" +

                        "¡Bienvenidos a nuestra Sauna para el Relax Total!\n" +
                        "\n" +
                        "-Saunas Variadas: Disfruta de una selección de saunas diseñadas para satisfacer tus preferencias. Desde saunas tradicionales de madera hasta saunas infrarrojas, cada una ofrece beneficios únicos para tu cuerpo y mente.\n" +
                        "\n" +
                        "-Ambiente Tranquilo: Sumérgete en un entorno tranquilo y sereno que te permite desconectar del estrés diario y relajar cuerpo y mente.\n" +
                        "\n" +
                        "-Beneficios para la Salud: Experimenta los múltiples beneficios para la salud que ofrece la sauna, desde la eliminación de toxinas hasta la mejora de la circulación sanguínea y la relajación muscular.\n" +
                        "\n" +
                        "Horario: 8:00-22:00\n" +
                        "\n" +
                        "¡Te esperamos para que disfrutes de un escape revitalizante y renovador!"

                ,
                style = TextStyle(
                    fontSize = 18.sp
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(22.dp)
                )
        }
    }
}