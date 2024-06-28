package com.example.mybusiness.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.mybusiness.presentation.navigation.Navigation
import com.example.mybusiness.theme.CustomAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            CustomAppTheme {
                Navigation(modifier = Modifier.fillMaxSize())
            }
        }
    }
}