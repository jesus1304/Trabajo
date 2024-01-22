import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Campos(navController: NavHostController) {

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
                        painter = painterResource(id = R.drawable.futbol5),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
            }
            Text(
                text = "Fútbol 5: ¡Velocidad y Habilidad!\n" +
                        "Bienvenido/a a [Nombre de la App], donde la acción se vuelve intensa en nuestros campos de fútbol 5. Ideal para partidos rápidos y emocionantes, nuestro campo de fútbol 5 es perfecto para aquellos que aman la velocidad y la destreza. Reserva tu espacio ahora y experimenta la emoción de jugar en un campo diseñado para la agilidad y el juego rápido entre amigos." ,
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
                        painter = painterResource(id = R.drawable.futbol7),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
            }
            Text(
                text = "Fútbol 7: Estrategia y Colaboración\n" +
                        "Descubre la magia del fútbol en nuestro campo de fútbol 7. Con más espacio para maniobrar, este campo fomenta la estrategia y la colaboración entre jugadores. Ideal para equipos que buscan un juego más táctico, reserva tu campo de fútbol 7 con [Nombre de la App] y experimenta la emoción de cada pase y gol en un entorno diseñado para la estrategia."
                ,
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
                        painter = painterResource(id = R.drawable.futbol11),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }
            }
            Text(
                text = "Fútbol 11: Desafío Épico en Grandes Dimensiones\n" +
                        "Si buscas el desafío definitivo, nuestro campo de fútbol 11 es la elección perfecta. Con dimensiones reglamentarias, este campo te permite sumergirte en la experiencia completa del fútbol. Reserva tu espacio ahora y prepárate para enfrentarte a equipos rivales en un entorno que exige lo mejor de tu habilidad y trabajo en equipo."
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