package com.example.crudv1.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Ajustes(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(50.dp))
            // Título de la pantalla
            Text(
                text = "Ajustes",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.size(50.dp))
            // Opciones de ajustes
            Button(
                onClick = { navController.navigate("MenuPPAL") },
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .height(50.dp)
                    .width(250.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = "Notificaciones",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.size(20.dp))

                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = null,
                        modifier = Modifier.size(50.dp).scale(2f)
                    )
                }
            }

            Button(
                onClick = { navController.navigate("MenuPPAL") },
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .height(50.dp)
                    .width(250.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = "Perfil",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.size(120.dp))

                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp).scale(2f)
                    )
                }
            }

            Button(
                onClick = { navController.navigate("MenuPPAL") },
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .height(50.dp)
                    .width(250.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = "Privacidad",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.size(60.dp))

                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp).scale(2f)
                    )
                }
            }

            Button(
                onClick = { navController.navigate("MenuPPAL") },
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .height(50.dp)
                    .width(250.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = "Ayuda",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.size(110.dp))

                    Icon(
                        imageVector = Icons.Filled.ThumbUp,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp).scale(2f)
                    )
                }
            }

            Button(
                onClick = { navController.navigate("MenuPPAL") },
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .height(50.dp)
                    .width(250.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = "Cerrar sesión",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.size(20.dp))

                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp).scale(2f)
                    )
                }
            }
        }
    }
}
