package com.example.upc2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upc2.repository.RepositoryJadwal
import com.example.upc2.data.entity.jadwal
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted

class HomeJadwalViewModel(
    private val repositoryJadwal: RepositoryJadwal
) : ViewModel() {

    val homeJadwalUiState: StateFlow<HomeJadwalUiState> = repositoryJadwal.getAllJadwal()
        .filterNotNull() // Pastikan repository mengembalikan data non-null
        .map { listJadwal ->
            HomeJadwalUiState(
                listJadwal = listJadwal.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(HomeJadwalUiState(isLoading = true))
            delay(900) // Delay untuk simulasi loading
        }
        .catch { exception ->
            emit(
                HomeJadwalUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = exception.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeJadwalUiState(isLoading = true)
        )
}

data class HomeJadwalUiState(
    val listJadwal: List<jadwal> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
