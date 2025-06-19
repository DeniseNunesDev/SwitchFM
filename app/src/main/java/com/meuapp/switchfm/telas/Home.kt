package com.meuapp.switchfm.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.meuapp.switchfm.R

@Composable
fun Home(navController: NavController) {
    val zenDots = FontFamily(Font(R.font.zen_dots))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF000000),
                        Color(0xFF300F1C)
                    )
                )
            )
            .padding(40.dp)
    ) {
        // Título no topo
        Text(
            text = "SwitchFM",
            style = MaterialTheme.typography.headlineLarge.copy(fontFamily = zenDots),
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate("loading") }
                .padding(24.dp)
        ) {
            Text(
                text = "Clique aqui para iniciar e sortear uma música",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(R.font.zen_dots)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF8B7980),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}
