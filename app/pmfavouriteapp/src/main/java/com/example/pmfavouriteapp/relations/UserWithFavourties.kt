package com.example.pmfavouriteapp.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.pmfavouriteapp.entities.ParliamentMember
import com.example.pmfavouriteapp.entities.User
import com.example.pmfavouriteapp.entities.UserFavouriteCrossRef

data class UserWithFavourites(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "hetekaId",
        associateBy = Junction(UserFavouriteCrossRef::class)
    )
    val favourites: List<ParliamentMember>
)