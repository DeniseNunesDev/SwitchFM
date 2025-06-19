package com.meuapp.switchfm.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.meuapp.switchfm.R

@Composable
fun ReturnScreen(navController: NavController) {
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
    ) {
        // Botão de return no canto superior esquerdo
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar",
                tint = Color.White
            )
        }

        // "SwitchFM" no canto superior direito, alinhado visualmente com o da tela de KaraokePlayer
        Text(
            text = "SwitchFM",
            fontFamily = zenDots,
            color = Color.White.copy(alpha = 0.4f),
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 23.dp, end = 20.dp) // alinhamento mais baixo e mesmo end
        )

        // Conteúdo centralizado
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Parabéns!",
                fontFamily = zenDots,
                fontSize = 36.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Desafio cumprido.",
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        // Botão no fim da tela
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .fillMaxWidth(0.8f)
                .background(Color(0x66010000), shape = MaterialTheme.shapes.medium) // #010000 com 40% opacidade
        ) {
            Button(
                onClick = { navController.navigate("loading") },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    "Sortear novamente",
                    fontFamily = zenDots
                )
            }
        }
    }
}
