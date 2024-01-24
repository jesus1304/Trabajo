package com.example.crudv1.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = { Text("Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) }
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
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
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Filled.Email else Icons.Filled.Email,
                        contentDescription = null
                    )
                }
            }
        )

        Button(
            onClick = {
                // Handle Sign In button click
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(48.dp)
                .background(Color(0xFF909090))
        ) {
            Text("Sign In", color = Color(0xFF121212))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Divider(color = Color(0xFF909090), thickness = 1.dp)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SocialButton(icon = Icons.Default.MailOutline, onClick = {
                // Handle Email button click
            })
            SocialButton(icon = Icons.Default.Face, onClick = {
                // Handle Facebook button click
            })
            SocialButton(icon = Icons.Filled.Email, onClick = {
                // Handle Instagram button click
            })
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Do you have an account? ",
                color = Color(0xFF909090)
            )
            Text(
                "Log In",
                color = Color.Red
            )
        }
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
