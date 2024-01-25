package com.example.crudv1.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.crudv1.R
import com.example.crudv1.navigation.SessionManager
import com.example.crudv1.ui.theme.typography
import com.google.firebase.firestore.FirebaseFirestore


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavHostController) {
    var user by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF121212))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF121212))
        ) {

            Text(
                text = "MERRYSERVICE",
                color = Color.White,
                style = typography.bodyLarge.copy(fontSize = 40.sp),
                modifier = Modifier.padding(top = 40.dp, start = 60.dp)
            )

            TextField(
                value = user,
                onValueChange = { user = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp, start = 24.dp, end = 24.dp)
                    .background(color = Color(0xFF909090), shape = RoundedCornerShape(12.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color(0xFF121212),
                    unfocusedIndicatorColor = Color(0xFF121212),
                    disabledIndicatorColor = Color(0xFF121212),
                    errorIndicatorColor = Color(0xFF121212),
                    cursorColor = Color(0xFF121212),


                    ),
                placeholder = { Text("User",) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null) }
            )

            TextField(
                value = contraseña,
                onValueChange = { contraseña = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                    .background(color = Color(0xFF909090), shape = RoundedCornerShape(12.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color(0xFF121212),
                    unfocusedIndicatorColor = Color(0xFF121212),
                    disabledIndicatorColor = Color(0xFF121212),
                    errorIndicatorColor = Color(0xFF121212),
                    cursorColor = Color(0xFF121212),
                ),
                placeholder = { Text("Password") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                trailingIcon = {
                    IconButton(
                        onClick = { isPasswordVisible = !isPasswordVisible },
                        modifier = Modifier.semantics {
                            if (isPasswordVisible) {

                            } else {

                            }

                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.candado),
                            contentDescription = "candado"
                        )
                    }
                }
            )
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
                                            navController.navigate("MenuPPAL") // Navega a la página de inicio

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
                modifier = Modifier
                    .background(color = Color(0xFF121212), shape = RoundedCornerShape(12.dp))
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Sign In", color = Color(0xFF121212))

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .padding(start = 24.dp)
                )
                Text(
                    text = "OR",
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .padding(end = 24.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 74.dp, top = 30.dp, end = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(44.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Logo de Facebook",
                )

                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Logo de Google"
                )

                Image(
                    painter = painterResource(id = R.drawable.instagram),
                    contentDescription = "Logo de Instagram"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Don´t have an account? ",
                    color = Color(0xFF909090)
                )
                Text(
                    "Sign Up",
                    color = Color.Red,
                    modifier = Modifier.clickable {
                        navController.navigate("ClienteGuardar")
                    }
                )
            }
        }
        // Fondo de la pantalla
        Image(
            painter = painterResource(id = R.drawable.inicio),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(-1f)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        // Resto de tu contenido va aquí
        // Puedes colocar otros composable encima de la imagen de fondo
    }
}



@Composable
fun SocialButton(icon: ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(48.dp)
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.background, shape = MaterialTheme.shapes.small),
        contentPadding = PaddingValues(0.dp),
        elevation = null
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}
