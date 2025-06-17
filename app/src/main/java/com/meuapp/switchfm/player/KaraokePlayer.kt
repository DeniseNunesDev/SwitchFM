package com.meuapp.switchfm.player

import android.widget.VideoView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.meuapp.switchfm.data.musicasKaraoke

@Composable
fun KaraokePlayer() {
    val context = LocalContext.current

    // Armazena a música atual (inicialmente aleatória)
    var musicaAtual by remember { mutableStateOf(musicasKaraoke.random()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Nome da música
        Text(
            text = "🎤 ${musicaAtual.nome}",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // PLAYER DE VÍDEO (recarrega quando a música muda)
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            factory = {
                VideoView(it).apply {
                    setVideoPath("android.resource://${context.packageName}/${musicaAtual.videoResId}")
                    setOnPreparedListener { it.isLooping = false }
                    start()
                }
            },
            // Força recriação quando a música muda
            update = { view ->
                view.setVideoPath("android.resource://${context.packageName}/${musicaAtual.videoResId}")
                view.setOnPreparedListener { it.isLooping = false }
                view.start()
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // LETRA DA MÚSICA
        Column {
            musicaAtual.letra.forEach {
                Text(text = it, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // BOTÃO: Sorteia nova música
        Button(
            onClick = {
                // Sorteia uma nova música diferente da atual
                var novaMusica = musicaAtual
                while (novaMusica == musicaAtual && musicasKaraoke.size > 1) {
                    novaMusica = musicasKaraoke.random()
                }
                musicaAtual = novaMusica
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🎲 Nova Música")
        }
    }
}
