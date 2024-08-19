package com.example.practice0819_2

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.IOException

class Map(
    private val userLocation: LatLng,
    private val zoomLevel: Float
) {

    // 避難所のリスト
    private val shelterPoints: MutableList<ShelterPoint> = mutableListOf()

    // 避難所のデータクラス
    data class ShelterPoint(val name: String, val latitude: Double, val longitude: Double)

    // 初期化時にCSVファイルを読み込んで避難所リストに追加
    // 現在地から半径3キロ以内を取得
    @Composable
    fun Content(modifier: Modifier = Modifier) {
        val context = LocalContext.current
        try {
            val inputStream = context.resources.openRawResource(R.raw.shelters_kuma)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val header = reader.readLine() // ヘッダーを取得
            val headerTokens = header.split(",")

            // 必要なカラムのインデックスを取得
            val nameIndex = headerTokens.indexOf("施設・場所名")
            val latitudeIndex = headerTokens.indexOf("緯度")
            val longitudeIndex = headerTokens.indexOf("経度")

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                val tokens = line!!.split(",")

                val name = tokens.getOrNull(nameIndex)
                val latitude = tokens.getOrNull(latitudeIndex)?.toDoubleOrNull()
                val longitude = tokens.getOrNull(longitudeIndex)?.toDoubleOrNull()

                if (name != null && latitude != null && longitude != null) {
                    shelterPoints.add(ShelterPoint(name, latitude, longitude))
                    // デバッグ用のログ出力
                    println("Added shelter: $name at ($latitude, $longitude)")
                } else {
                    println("Invalid shelter data: $line")
                }
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // カメラポジションの初期設定
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(userLocation, zoomLevel)
        }

        GoogleMap(
            modifier = modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // 初期ピンを立てる
            Marker(
                position = userLocation,
                title = "現在地",
                snippet = "あなたの現在地"
            )

            // 避難所のピンを立てる
            shelterPoints.forEach { shelter ->
                Marker(
                    position = LatLng(shelter.latitude, shelter.longitude),
                    title = shelter.name,
                    snippet = "避難所"
                )
            }
        }
    }

}
