package com.meuapp.switchfm.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.navigation.NavController
import com.meuapp.switchfm.R

@Composable
fun Loading(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(1200)
        navController.navigate("karaoke") {
            popUpTo("loading") { inclusive = true }
        }
    }
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
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.wrapContentHeight().wrapContentWidth()
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "loading...",
                fontFamily = zenDots,
                fontSize = 32.sp,
                color = Color(0xFF8B7980),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }
}
