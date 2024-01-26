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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R

@Composable
fun StartNow(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo de imagen
        Image(
            painter = painterResource(id = R.drawable.garcis3),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Contenido superpuesto
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Texto "MERRYSERVICE"
            Text(
                text = "MERRYSERVICE",
                style = androidx.compose.ui.text.TextStyle(
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 86.dp,top=20.dp)
            )

            // Imagen
            Image(
                painter = painterResource(id = R.drawable.merryservice2),
                contentDescription = null,
                modifier = Modifier
                    .size(375.dp)
                    .padding(bottom = 156.dp),
                contentScale = ContentScale.Crop
            )

            // Botón "START NOW!!!"
            Button(
                onClick = { navController.navigate("Login") },
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .height(100.dp)
                    .width(300.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "START NOW!!!",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.size(0.dp))

            // Texto "Do you have an account? Log in-->"
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Do you have an account?",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "Log in-->",
                    color = Color.Red,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("MenuPPAL") // Ajusta la acción de navegación según sea necesario
                        }
                )
            }
        }
    }
}

