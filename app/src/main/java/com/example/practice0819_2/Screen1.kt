package com.example.practice0819_2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.CameraPosition

class Screen1 {
    private val user = User()
    private val map = Map(user.location, 10f)

    @Composable
    fun Content() {

        // カメラポジションの初期設定
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(user.location, 10f)
        }

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
                Text(text = "現在地の座標: ${user.location.latitude}, ${user.location.longitude}")

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    // ボタンのクリック処理をここに記述
                }) {
                    Text(text = "ボタン")
                }
            }
        }
    }
}
