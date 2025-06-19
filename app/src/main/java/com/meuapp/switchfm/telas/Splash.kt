package com.meuapp.switchfm.telas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.navigation.NavController
import com.meuapp.switchfm.R

@Composable
fun Splash(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(1500)
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF000000), // Preto em cima
                        Color(0xFF300F1C)  // Roxo escuro embaixo
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
             painter = painterResource(R.drawable.union_2_),
             contentDescription = "Logo Splash",
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp)
        )
    }
}
