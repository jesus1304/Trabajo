package com.example.crudv1.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R

@Composable
fun MenuPPAL(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally // Alinea el contenido horizontalmente
        ) {

            Text(
                text = "MERRYSERVICE",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.size(56.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center // Centra horizontalmente los elementos en el Row
            ) {
                Card(
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                ) {
                    IconButton(
                        onClick = { navController.navigate("StartNow") },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.facturas),
                            contentDescription = null,
                            modifier = Modifier
                                .size(125.dp),
                            tint = (Color.Black)
                        )
                    }
                }
                Spacer(modifier = Modifier.size(36.dp))
                Card(
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                ) {
                    IconButton(
                        onClick = { navController.navigate("") },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = null,
                            modifier = Modifier
                                .size(125.dp),
                            tint = (Color.Black)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center
            ) {

                Button(
                    onClick = { navController.navigate("StartNow") },
                    modifier = Modifier
                        .height(40.dp) // Ajusta la altura según sea necesario
                        .width(150.dp)
                ) {
                    Text(
                        text = "Facturas",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.size(36.dp))
                Button(
                    onClick = { navController.navigate("") },
                    modifier = Modifier
                        .height(40.dp) // Ajusta la altura según sea necesario
                        .width(150.dp)
                ) {
                    Text(
                        text = "Horarios",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.size(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center // Centra horizontalmente los elementos en el Row
            ) {
                Card(
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                ) {
                    IconButton(
                        onClick = { navController.navigate("") },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.List,
                            contentDescription = null,
                            modifier = Modifier
                                .size(125.dp),
                            tint = (Color.Black)
                        )
                    }
                }
                Spacer(modifier = Modifier.size(36.dp))
                Card(
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                ) {
                    IconButton(
                        onClick = { navController.navigate("") },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MailOutline,
                            contentDescription = null,
                            modifier = Modifier
                                .size(125.dp),
                            tint = (Color.Black)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center
            ) {

                Button(
                    onClick = { navController.navigate("") },
                    modifier = Modifier
                        .height(40.dp) // Ajusta la altura según sea necesario
                        .width(150.dp)
                ) {
                    Text(
                        text = "Tareas",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                }
                Spacer(modifier = Modifier.size(36.dp))
                Button(
                    onClick = { navController.navigate("") },
                    modifier = Modifier
                        .height(40.dp) // Ajusta la altura según sea necesario
                        .width(150.dp)
                ) {
                    Text(
                        text = "Mensajes",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                }
            }
            Spacer(modifier = Modifier.size(200.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(Color.White),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically // Centra verticalmente los iconos
                ) {
                    IconButton(onClick = { navController.navigate("Home") }) {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = "Home",
                            modifier = Modifier.size(50.dp)
                        )
                    }

                    IconButton(onClick = { navController.navigate("Search") }) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Search",
                            modifier = Modifier.size(50.dp)
                        )
                    }

                    IconButton(onClick = { navController.navigate("Ajustes") }) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Settings",
                            modifier = Modifier.size(50.dp)
                        )
                    }

                    IconButton(onClick = { navController.navigate("Profile") }) {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "Person",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            }
        }
    }
}