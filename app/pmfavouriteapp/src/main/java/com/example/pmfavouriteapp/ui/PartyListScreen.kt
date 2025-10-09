package com.example.pmfavouriteapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pmfavouriteapp.R
import com.example.pmfavouriteapp.ui.viewmodel.PartyViewModel

@Composable
fun PartyListScreen(viewModel: PartyViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold{ innerPadding ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.errorMessage != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.message_error, uiState.errorMessage!!),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            uiState.parties.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(R.string.message_no_parties))
                }
            }

            else -> {
                Column(modifier = Modifier.padding(innerPadding)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(stringResource(R.string.label_favorite_members, uiState.favoriteCount))
                        if (uiState.canFormMajority) {
                            Text(
                                stringResource(R.string.label_can_form_majority),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(uiState.parties) { party ->
                            PartyItem(
                                party = party,
                                onToggleFavorite = { viewModel.toggleFavorite(party) }
                            )
                        }
                    }
                }
            }
        }
    }
}
