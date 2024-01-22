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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inicio2(navController: NavHostController) {
    var expanded by remember{ mutableStateOf(false) }
    val options=listOf(
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
                        expanded=expanded,
                        onDismissRequest={expanded=false}){
                        options.forEach{ option->
                            DropdownMenuItem(
                                text={Text(text=option)},
                                onClick = { when (option) {
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
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.logo2),
                    contentDescription = "Descripción de la imagen",
                    modifier = Modifier
                        .padding(20.dp)
                        .height(70.dp),

                    )
            }
            Card(
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Button(
                        onClick = { navController.navigate("Campos") },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Campos",
                            fontSize = 18.sp,
                            )
                    }
                    Button(
                        onClick = { navController.navigate("MenuInicio2") },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Posibles posiciones",
                            fontSize = 18.sp,

                            )
                    }
                }
            }
            Image(
                painter = painterResource(id = R.drawable.campo),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)

            )
        }
    }
}

