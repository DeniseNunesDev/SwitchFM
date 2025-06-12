package com.meuapp.swithcfmapp.uiall


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.meuapp.swithcfmapp.navigation.Screen
import com.example.switchfm.repository.MusicRepository
import com.meuapp.swithcfmapp.R
import kotlinx.coroutines.delay
import androidx.compose.animation.core.*

@Composable
fun Loading(navController: NavController) {
    val selectedMusic = remember { MusicRepository.getRandomMusic() }

    // Efeito de animação "pulsando"
    val scale = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        // Loop de animação
        while (true) {
            scale.animateTo(
                targetValue = 1.2f,
                animationSpec = tween(durationMillis = 500)
            )
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 500)
            )
        }
    }

    // Delay e navegação
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("${Screen.Result.route}/${selectedMusic.name}/${selectedMusic.spotifyUrl}")
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_compact___14),
            contentDescription = "tela de carregamento",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(200.dp)
                .scale(scale.value)
        )
    }
}
