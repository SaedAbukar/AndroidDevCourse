package com.example.pmfavouriteapp.ui.models

import com.example.pmfavouriteapp.R
import com.example.pmfavouriteapp.data.model.Party

data class PartyUiModel(
    val name: String,
    val memberCount: Int,
    val favorite: Boolean,
    val imageResId: Int
) {
    companion object {
        fun fromParty(party: Party): PartyUiModel {
            val image = when (party.name) {
                "kok" -> R.drawable.kok
                "sd" -> R.drawable.sdp
                "ps" -> R.drawable.ps
                "kesk" -> R.drawable.kesk
                "vihr" -> R.drawable.vihr
                "vas" -> R.drawable.vas
                "r" -> R.drawable.rkp
                "kd" -> R.drawable.kd
                "liik" -> R.drawable.liik
                else -> R.drawable.parliament
            }
            return PartyUiModel(
                name = party.name,
                memberCount = party.memberCount,
                favorite = party.favorite,
                imageResId = image
            )
        }
    }
}