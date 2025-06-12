package com.example.switchfm.repository

import com.meuapp.swithcfmapp.model.Music

object MusicRepository {
    val musics = listOf(
        Music("Blinding Lights", "https://open.spotify.com/track/0VjIjW4GlUZAMYd2vXMi3b"),
        Music("Levitating", "https://open.spotify.com/track/463CkQjx2Zk1yXoBuierM9"),
        Music("Peaches", "https://open.spotify.com/track/4iJyoBOLtHqaGxP12qzhQI"),
        Music("Watermelon Sugar", "https://open.spotify.com/track/6UelLqGlWMcVH1E5c4H7lY"),
        Music("Save Your Tears", "https://open.spotify.com/track/5QO79kh1waicV47BqGRL3g"),
        Music("drivers license", "https://open.spotify.com/track/5wANPM4fQCJwkGd4rN57mH"),
        Music("Dance Monkey", "https://open.spotify.com/track/2XU0oxnq2qxCpomAAuJY8K"),
        Music("Stay", "https://open.spotify.com/track/5PjdY0CKGZdEuoNab3yDmX"),
        Music("As It Was", "https://open.spotify.com/track/4LRPiXqCikLlN15c3yImP7"),
        Music("Unholy", "https://open.spotify.com/track/3nqQXoyQOWXiEszozefZ9S")
    )

    fun getRandomMusic(): Music {
        return musics.random()
    }
}


//aqui, se cria um repositório de músicas