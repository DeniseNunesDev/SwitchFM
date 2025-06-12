package com.meuapp.swithcfmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.meuapp.swithcfmapp.navigation.Screen
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.meuapp.swithcfmapp.ui.ResultScreen
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import kotlinx.coroutines.delay
import android.view.animation.OvershootInterpolator
import androidx.compose.ui.tooling.preview.Preview
import com.meuapp.swithcfmapp.R
import com.meuapp.swithcfmapp.uiall.Loading

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation()
                }
            }
        }
    }
}



@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1500L)
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        Image(
            painter = painterResource(id = R.drawable.vector_1_), // 🔥 Coloque sua imagem
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value)
                .size(200.dp)
        )
    }
}

@Preview
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Home.route) {
            Home(navController)
        }
        composable(Screen.Loading.route) {
            Loading(navController)
        }
        composable(
            "${Screen.Result.route}/{musicName}/{spotifyUrl}",
            arguments = listOf(
                navArgument("musicName") { type = NavType.StringType },
                navArgument("spotifyUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            ResultScreen(
                navController = navController,
                musicName = backStackEntry.arguments?.getString("musicName") ?: "",
                spotifyUrl = backStackEntry.arguments?.getString("spotifyUrl") ?: ""
            )
        }
    }
}



