package com.meuapp.swithcfmapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.meuapp.swithcfmapp.navigation.Screen



@Composable
fun Home(navController: NavController) {
    Scaffold { paddingValues ->

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 🔥 Imagem da Home
            Image(
                painter = painterResource(id = R.drawable.android_compact___2),
                contentDescription = "Imagem da Home",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
        }

        // Container para centralizar o conteúdo
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    navController.navigate(Screen.Loading.route)
                }
            ) {
                Text(text = "Sortear Música")
            }

        }
    }
}



