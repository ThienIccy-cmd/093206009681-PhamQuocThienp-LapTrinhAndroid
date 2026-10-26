package com.example.bt01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bt01.navigation.AppNavHost
import com.example.bt01.ui.theme.Bt01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bt01Theme {
                AppNavHost()
            }
        }
    }
}
