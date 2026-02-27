package com.jetpackcomposeroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jetpackcomposeroom.ui.theme.JetPackComposeRoomTheme
import com.jetpackcomposeroom.viewModel.PostViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: PostViewModel by viewModels()
    //https://medium.com/@dheerubhadoria/jetpack-compose-android-app-with-mvvm-architecture-and-retrofit-api-integration-4eb61ca6fbf2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostScreen(viewModel)
        }
    }
}
