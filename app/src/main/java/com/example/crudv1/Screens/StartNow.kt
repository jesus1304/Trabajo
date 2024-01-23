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

    }
}