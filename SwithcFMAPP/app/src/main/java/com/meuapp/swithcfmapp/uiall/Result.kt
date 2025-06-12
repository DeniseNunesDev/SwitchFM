package com.meuapp.swithcfmapp.uiall

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.meuapp.swithcfmapp.R
import com.meuapp.swithcfmapp.navigation.Screen
import android.media.MediaPlayer
import androidx.compose.ui.platform.LocalContext
import com.example.switchfm.repository.MusicRepository


@Composable
fun ResultScreen(navController: NavController) {
    val context = LocalContext.current
    val music = remember { MusicRepository.getRandomMusic() }

    // MediaPlayer para tocar a prévia
    val mediaPlayer = remember {
        MediaPlayer().apply {
            setDataSource(music.previewUrl)
            prepare()
            start()
        }
    }

    // Limpa o MediaPlayer quando sair da tela
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    var showPopup by remember { mutableStateOf(false) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("🎵 Música Sorteada:", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))
            Text(music.name, style = MaterialTheme.typography.bodyLarge)

            Spacer(Modifier.height(24.dp))

            Button(onClick = { showPopup = true }) {
                Text("Ouvir no Spotify")
            }

            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(0)
                }
            }) {
                Text("Sortear Novamente")
            }
        }

        // Pop-up para abrir link no Spotify
        if (showPopup) {
            AlertDialog(
                onDismissRequest = { showPopup = false },
                confirmButton = {
                    TextButton(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(music.spotifyUrl))
                        context.startActivity(intent)
                        showPopup = false
                    }) {
                        Text("Abrir Spotify")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showPopup = false }) {
                        Text("Cancelar")
                    }
                },
                title = { Text("Ouvir no Spotify") },
                text = { Text("Deseja abrir essa música no Spotify?") }
            )
        }
    }
}

