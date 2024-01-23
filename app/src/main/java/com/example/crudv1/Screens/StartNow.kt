package com.example.crudv1.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R
import java.time.format.TextStyle

@Composable
fun StartNow(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)

    ){
        Text(
            text = "MERRYSERVICE",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(55.dp),
            style = androidx.compose.ui.text.TextStyle(
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        )
            Image(
                painter = painterResource(id = R.drawable.merryservice2),
                contentDescription = null,
                modifier = Modifier
                    .size(400.dp)
                    .align(Alignment.Center)
                    .offset(y = (-100).dp),
                )
        Button(
            onClick = { /* Acción al hacer clic en el botón */ },
            modifier = Modifier

                .align(Alignment.BottomCenter)
                .padding(bottom = 126.dp)
                .height(100.dp)
                .width(300.dp)
                .background(color = Color.Black, shape = RoundedCornerShape(12.dp)),
                    colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.White
        )
        ) {
            Text(
                text = "START NOW!!!",
                color = Color.Black,
                fontWeight = FontWeight.Bold ,
                style = androidx.compose.ui.text.TextStyle(
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
            )


        }
        Text(
            text=""
        )

    }
}