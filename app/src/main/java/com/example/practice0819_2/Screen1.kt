package com.example.practice0819_2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


class Screen1 {
    private val map = Map(15f)

    @Composable
    fun Content(navController: NavHostController) {

        Column(modifier = Modifier.fillMaxSize()) {
            // 上部70%にGoogle Mapを表示
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
            ) {
                map.Content()
            }

            // 下部30%に現在地の座標とボタンを配置
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "現在地の座標: ${map.user.location.latitude}, ${map.user.location.longitude}")

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    // ボタンのクリック処理をここに記述
                    navController.navigate("screen2")

                }) {
                    Text(text = "ボタン")
                }
            }
        }
    }
}
