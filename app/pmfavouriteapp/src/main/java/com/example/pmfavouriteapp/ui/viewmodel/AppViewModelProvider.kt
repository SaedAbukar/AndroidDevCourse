package com.example.pmfavouriteapp.ui.viewmodel

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pmfavouriteapp.MyApp

object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            val app = MyApp.getInstance()
            val container = app.container

            UserViewModel(
                userRepository = container.userRepository,
                pmRepository = container.parliamentRepository
            )
        }
    }
}

