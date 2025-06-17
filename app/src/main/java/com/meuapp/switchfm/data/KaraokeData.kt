package com.meuapp.switchfm.data

import com.meuapp.switchfm.R

data class KaraokeMusica(
    val nome: String,
    val videoResId: Int,
    val letra: List<String>
)

val musicasKaraoke = listOf(
    KaraokeMusica(
        nome = "Aquarela - Toquinho",
        videoResId = R.raw.aquarela,
        letra = listOf(
        )
    ),
KaraokeMusica(
nome = "Outra Música",
videoResId = R.raw.baby,
letra = listOf(
)
),
KaraokeMusica(
nome = "Outra Música",
videoResId = R.raw.espresso,
letra = listOf(
)
),
KaraokeMusica(
nome = "Outra Música",
videoResId = R.raw.evidencias,
letra = listOf(
)
)
)
