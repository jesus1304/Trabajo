package com.example.crudv1.Screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun Inicio(navController: NavHostController) {
    val onClickImage: () -> Unit = {
        navController.navigate("Futbol")
    }
    val onClickImage2: () -> Unit = {
        navController.navigate("Piscina")
    }
    val onClickImage3: () -> Unit = {
        navController.navigate("Gimnasio")
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Sad Ocio Deporte Canal")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Localizacion") }) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location Icon"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("MenuInicio") }) {

                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Location Icon"
                        )
                    }
                }
            )
        },

        ) { innerPadding ->
        Card(
            modifier = Modifier.padding(top = 23.dp, bottom = 10.dp),
        )
        {
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {


                Row(
                    modifier = Modifier.clickable(onClick = onClickImage),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.campo),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(165.dp)
                    )
                }

                Row(
                    modifier = Modifier.clickable(onClick = onClickImage2),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.piscina),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )
                }
                Row(
                    modifier = Modifier.clickable(onClick = onClickImage3),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gym),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )
                }
            }
        }
    }
}
