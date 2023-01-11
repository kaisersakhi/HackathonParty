package com.kaisersakhi.hackathonparty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kaisersakhi.hackathonparty.ui.screens.MainScreen
import com.kaisersakhi.hackathonparty.ui.theme.HackathonPartyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackathonPartyTheme {
                MainScreen()
            }
        }
    }
}

