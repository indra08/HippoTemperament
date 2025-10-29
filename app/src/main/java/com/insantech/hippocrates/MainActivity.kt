package com.insantech.hippocrates

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.insantech.hippocrates.navigation.NavGraph
import com.insantech.hippocrates.ui.theme.HippocratesTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HippocratesTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier) {
                    NavGraph(navController)
                }
            }
        }
    }
}
