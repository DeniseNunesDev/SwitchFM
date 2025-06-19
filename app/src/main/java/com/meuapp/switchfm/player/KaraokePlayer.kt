package com.meuapp.switchfm.player

import android.media.MediaPlayer
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meuapp.switchfm.R
import com.meuapp.switchfm.data.musicasKaraoke
import androidx.navigation.NavController

@Composable
fun KaraokePlayer(navController: NavController? = null) {
    val context = LocalContext.current
    var musicaAtual by remember { mutableStateOf(musicasKaraoke.random()) }
    var isPlaying by remember { mutableStateOf(false) }
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    // Toca o áudio automaticamente ao trocar de música
    LaunchedEffect(musicaAtual) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(
            context,
            musicaAtual.videoResId
        )
        mediaPlayer?.start()
        isPlaying = true
    }

    Column(
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
            .padding(16.dp)
    ) {
        val zenDots = FontFamily(Font(R.font.zen_dots))
        // Botão de voltar para Home no canto superior esquerdo
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = { navController?.navigate("home") },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar para Home",
                    tint = Color.White
                )
            }
            // "SwitchFM" no canto superior direito, pequeno e com contraste 40%
            Text(
                text = "SwitchFM",
                fontFamily = zenDots,
                color = Color.White.copy(alpha = 0.4f),
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 8.dp, end = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Nome da música acima da letra, com box opaca e fonte Zen Dots
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0x66010000), shape = MaterialTheme.shapes.medium)
                .padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            Text(
                text = musicaAtual.nome,
                style = MaterialTheme.typography.titleLarge,
                fontFamily = zenDots,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Letra da música com rolagem automática, centralizada e texto branco
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val scrollState = rememberScrollState()
            val letraSize = musicaAtual.letra.size
            val scrollMax = letraSize * 28 // Menor altura por linha para menos espaço

            // Animação de rolagem automática com delay inicial
            val infiniteTransition = rememberInfiniteTransition(label = "letraScroll")
            val scrollAnim by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = scrollMax.toFloat(),
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = letraSize * 2000, // Mais devagar
                        delayMillis = 1200, // Delay inicial maior
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart
                ), label = "letraScrollAnim"
            )

            LaunchedEffect(scrollAnim) {
                scrollState.scrollTo(scrollAnim.toInt())
            }

            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                musicaAtual.letra.forEach {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botões de Pause e Skip (sem box grande)
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    mediaPlayer?.pause()
                    isPlaying = false
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF010000))
            ) {
                Text("Pause")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    // Apenas navega para a tela de return, não troca a música
                    navController?.navigate("return")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF010000))
            ) {
                Text("Skip")
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }
}
