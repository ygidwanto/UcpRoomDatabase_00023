package com.example.upc2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.upc2.RsApp

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeJdwlViewModel(
                RsApp().containerApp.repositoryJadwal
            )
        }
        initializer {
            HomeJdwlViewModel(
                RsApp().containerApp.repositoryJadwal,
            )
        }
        initializer {
            UpdateJadwalViewModel(
                createSavedStateHandle(),
                RsApp().containerApp.repositoryJadwal,
            )
        }
    }
}

fun CreationExtras.RsApp(): RsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RsApp)
