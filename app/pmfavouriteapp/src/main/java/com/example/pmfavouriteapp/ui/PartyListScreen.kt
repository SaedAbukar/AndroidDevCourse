package com.example.pmfavouriteapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pmfavouriteapp.ui.viewmodel.PartyViewModel

@Composable
fun PartyListScreen(viewModel: PartyViewModel) {
    val parties = viewModel.parties.collectAsState()
    val favoriteCount = viewModel.favoriteCount.collectAsState()
    val majority = viewModel.majority.collectAsState()

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Favorite Members: ${favoriteCount.value}")
                if (majority.value) {
                    Text("Can form majority!", color = MaterialTheme.colorScheme.primary)
                }
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(parties.value) { party ->
                    PartyItem(
                        party = party,
                        onToggleFavorite = { viewModel.toggleFavorite(party) }
                    )
                }
            }
        }
    }
}