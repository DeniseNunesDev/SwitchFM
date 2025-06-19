package com.meuapp.switchfm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.meuapp.switchfm.player.KaraokePlayer
import com.meuapp.switchfm.telas.Home
import com.meuapp.switchfm.telas.Loading
import com.meuapp.switchfm.telas.ReturnScreen
import com.meuapp.switchfm.telas.Splash
import com.meuapp.switchfm.ui.theme.SwitchFMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwitchFMTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { Splash(navController) }
        composable("home") { Home(navController) }
        composable("loading") { Loading(navController) }
        composable("karaoke") { KaraokePlayer(navController) }
        composable("return") { ReturnScreen(navController) }
    }
}
