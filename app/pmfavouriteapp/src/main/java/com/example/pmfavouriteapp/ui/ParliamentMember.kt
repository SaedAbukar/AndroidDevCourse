package com.example.pmfavouriteapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pmfavouriteapp.entities.ParliamentMember
import com.example.pmfavouriteapp.ui.viewmodel.UserViewModel

@Composable
fun ParliamentApp(modifier: Modifier = Modifier, viewModel: UserViewModel) {
    val members by viewModel.allMembers.collectAsState()
    val userFavourites by viewModel.userFavourites.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(members) { member ->
            val isFavourite = userFavourites?.favourites?.any { it.hetekaId == member.hetekaId } == true
            MemberRow(
                member = member,
                isFavourite = isFavourite,
                onToggleFavourite = { viewModel.toggleFavourite(member) }
            )
        }
    }
}

@Composable
fun MemberRow(
    modifier: Modifier = Modifier,
    member: ParliamentMember,
    isFavourite: Boolean,
    onToggleFavourite: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text("${member.firstname} ${member.lastname}", style = MaterialTheme.typography.titleMedium)
            Text(member.party, style = MaterialTheme.typography.bodySmall)
        }

        IconButton(onClick = onToggleFavourite) {
            Icon(
                imageVector = if (isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = if (isFavourite) "Remove from favourites" else "Add to favourites"
            )
        }
    }
}
