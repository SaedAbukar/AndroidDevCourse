package com.example.pmfavouriteapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmfavouriteapp.entities.ParliamentMember
import com.example.pmfavouriteapp.entities.User
import com.example.pmfavouriteapp.repository.ParliamentRepository
import com.example.pmfavouriteapp.repository.UserRepository
import com.example.pmfavouriteapp.repository.NetworkParliamentRepository
import com.example.pmfavouriteapp.relations.UserWithFavourites
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository,
    private val pmRepository: ParliamentRepository,
    private val networkRepository: NetworkParliamentRepository
) : ViewModel() {

    private val _userFavourites = MutableStateFlow<UserWithFavourites?>(null)
    val userFavourites: StateFlow<UserWithFavourites?> = _userFavourites

    private val _allMembers = MutableStateFlow<List<ParliamentMember>>(emptyList())
    val allMembers: StateFlow<List<ParliamentMember>> = _allMembers

    init {
        viewModelScope.launch {
            val users = userRepository.getUsersWithFavourites().first()
            if (users.isEmpty()) {
                userRepository.addUser(User(userId = 1, username = "Default User"))
            }
        }
        loadUserFavourites()
        syncParliamentMembers()
    }

    private fun loadUserFavourites() {
        viewModelScope.launch {
            userRepository.getUsersWithFavourites().collectLatest { usersWithFavs ->
                _userFavourites.value = usersWithFavs.firstOrNull()
            }
        }
    }

    private fun syncParliamentMembers() {
        viewModelScope.launch {
            pmRepository.getAllMembers().collectLatest { members ->
                if (members.isEmpty()) {
                    val remoteMembers = networkRepository.fetchAllMembers()
                    pmRepository.addAll(remoteMembers)
                } else {
                    _allMembers.value = members
                }
            }
        }
    }

    fun toggleFavourite(member: ParliamentMember) {
        val currentUser = _userFavourites.value ?: return
        val isFavourite = currentUser.favourites.any { it.hetekaId == member.hetekaId }

        viewModelScope.launch {
            if (isFavourite) {
                userRepository.removeFavourite(currentUser.user.userId, member.hetekaId)
                _userFavourites.value = currentUser.copy(
                    favourites = currentUser.favourites.filter { it.hetekaId != member.hetekaId }
                )
            } else {
                userRepository.addFavourite(currentUser.user.userId, member.hetekaId)
                _userFavourites.value = currentUser.copy(
                    favourites = currentUser.favourites + member
                )
            }
        }
    }
}
