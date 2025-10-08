package com.example.pmfavouriteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pmfavouriteapp.ui.PartyListScreen
import com.example.pmfavouriteapp.ui.theme.ComposeBasicsTheme
import com.example.pmfavouriteapp.ui.viewmodel.AppViewModelProvider
import com.example.pmfavouriteapp.ui.viewmodel.PartyViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicsTheme {
                val partyViewModel: PartyViewModel = viewModel(factory = AppViewModelProvider.Factory)

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        PartyListScreen(partyViewModel)
                    }
                }
            }
        }
    }
}
