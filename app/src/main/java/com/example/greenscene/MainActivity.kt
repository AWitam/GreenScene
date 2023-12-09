package com.example.greenscene
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.example.greenscene.ui.theme.GreenSceneTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            GreenSceneTheme {
                // A surface container using the 'background' color from the theme
                GreenSceneApp()
            }
        }
    }

}
