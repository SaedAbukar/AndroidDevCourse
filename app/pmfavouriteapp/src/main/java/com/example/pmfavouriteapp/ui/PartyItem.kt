package com.example.pmfavouriteapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pmfavouriteapp.data.model.Party

@Composable
fun PartyItem(party: Party, onToggleFavorite: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleFavorite() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = party.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Members: ${party.memberCount}", style = MaterialTheme.typography.bodyMedium)
        }

        Icon(
            imageVector = if (party.favorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = if (party.favorite) "Unmark favorite" else "Mark favorite",
            tint = if (party.favorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}