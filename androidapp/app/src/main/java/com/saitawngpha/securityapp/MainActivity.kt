package com.saitawngpha.securityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.saitawngpha.securityapp.screen.MainScreen
import com.saitawngpha.securityapp.screen.MainViewModel
import com.saitawngpha.securityapp.ui.theme.SecurityAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecurityAppTheme {
                // A surface container using the 'background' color from the theme
                val viewModel: MainViewModel = hiltViewModel()
                val apiKeysReady by viewModel.apiKeysReady
                val apiKeys by viewModel.apiKeys
                MainScreen(
                    apiKeysReady = apiKeysReady,
                    apiKeys = apiKeys,
                    onTryAgain = {viewModel.fetchData()}
                )
            }
        }
    }
}