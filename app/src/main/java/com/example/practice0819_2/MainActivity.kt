package com.example.practice0819_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main") {
                composable("main") { MainScreen(navController) }
                composable("screen1") { Screen1().Content() }
                composable("screen2") { Screen2().Content() }
                composable("screen3") { Screen3().Content() }
                composable("screen4") { Screen4().Content() }
            }
        }
    }

    @Composable
    fun MainScreen(navController: androidx.navigation.NavHostController) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navController.navigate("screen1") }) {
                Text("画面1へ")
            }
            Button(onClick = { navController.navigate("screen2") }) {
                Text("画面2へ")
            }
            Button(onClick = { navController.navigate("screen3") }) {
                Text("画面3へ")
            }
            Button(onClick = { navController.navigate("screen4") }) {
                Text("画面4へ")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainActivity().MainScreen(rememberNavController())
}