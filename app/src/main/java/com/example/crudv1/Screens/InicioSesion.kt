package com.example.crudv1.Screens
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crudv1.R
import com.example.crudv1.navigation.SessionManager
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun InicioSesion(navController:NavHostController) {

    Column(
        modifier = Modifier.fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = "Descripción de la imagen",
            modifier = Modifier
                .padding(start=155.dp,top=15.dp, bottom = 15.dp)
                .height(70.dp),
            )
        Image(
            painter = painterResource(id = R.drawable.campo2),
            contentDescription = "Login Image",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .padding(start=20.dp, bottom = 5.dp)
                .height(280.dp),
                    )
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center

                ) {
                Card(
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 50.dp)
                    )
                {

                    var user by rememberSaveable { mutableStateOf("") }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(5.dp)
                    ) {
                        val icon = Icons.Default.Person
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(44.dp),
                        )
                        OutlinedTextField(
                            value = user,
                            onValueChange = { user = it },
                            label = { Text("User") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                    var contraseña by rememberSaveable { mutableStateOf("") }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        val icon = Icons.Default.Lock
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(44.dp),
                        )
                        OutlinedTextField(
                            value = contraseña,
                            onValueChange = { contraseña = it },
                            label = { Text("Password") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation()
                        )
                    }
                    val db = FirebaseFirestore.getInstance()
                    val coleccion = "cliente"

                    var mensajeConfirmacion by remember { mutableStateOf("") }
                    val context = LocalContext.current

                    Button(
                        onClick = {
                            if (user.isNotBlank() && contraseña.isNotBlank()) {
                            } else {
                            }
                            db.collection(coleccion)
                                .whereEqualTo("user", user.toString())
                                .get()
                                .addOnSuccessListener { querySnapshot ->
                                    if (!querySnapshot.isEmpty) {
                                        var credentialsMatched = false
                                        for (documentSnapshot in querySnapshot) {
                                            val storedUser = documentSnapshot.getString("user")
                                            val storedContraseña =
                                                documentSnapshot.getString("contraseña")
                                            if (user == storedUser && contraseña == storedContraseña) {
                                                credentialsMatched = true
                                                SessionManager.setLoggedIn(context, true)
                                                SessionManager.setUsername(
                                                    context,
                                                    user
                                                )
                                                val isLoggedIn =
                                                    SessionManager.isLoggedIn(context)
                                                if (isLoggedIn) {
                                                    navController.navigate("Piscina") // Navega a la página de inicio

                                                }
                                                if (!credentialsMatched) {
                                                    mensajeConfirmacion = "Usuario o contraseña incorrectos"
                                                }
                                            } else {
                                            }
                                            }
                                        }

                                }
                                .addOnFailureListener {
                                    mensajeConfirmacion = "Error al verificar los datos"
                                }
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White
                        ),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier
                            .padding(start = 50.dp, top = 15.dp)
                            .width(350.dp)
                    ) {
                        Text(
                            text = "Entrar",
                            fontSize = 20.sp,
                        )
                    }

                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = mensajeConfirmacion,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(start=50.dp)
                    )

                    val text = "¿Aún no tienes usuario? Regístrate"

                    Text(
                        text = buildAnnotatedString {
                            append(text)
                            addStyle(
                                style = SpanStyle(
                                    color = Color.Blue,
                                    textDecoration = TextDecoration.Underline
                                ),
                                start = text.indexOf("Regístrate"),
                                end = text.length
                            )
                        },
                        modifier = Modifier.clickable {
                            navController.navigate("ClienteGuardar")
                        }
                            .padding(start = 70.dp)
                    )
                }
                }
            }
    }
}