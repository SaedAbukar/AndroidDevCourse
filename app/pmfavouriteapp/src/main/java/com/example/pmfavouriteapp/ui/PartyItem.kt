package com.example.pmfavouriteapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pmfavouriteapp.R
import com.example.pmfavouriteapp.ui.models.PartyUiModel

@Composable
fun PartyItem(party: PartyUiModel, onToggleFavorite: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleFavorite() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = party.imageResId),
                contentDescription = stringResource(R.string.content_description_party_logo, party.name),
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(text = party.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = stringResource(R.string.label_members, party.memberCount),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Icon(
            imageVector = if (party.favorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = if (party.favorite)
                stringResource(R.string.action_unmark_favorite)
            else
                stringResource(R.string.action_mark_favorite),
            tint = if (party.favorite)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurface
        )
    }
}
