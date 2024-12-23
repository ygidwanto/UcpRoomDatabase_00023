package com.example.upc2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upc2.data.entity.dokter
import com.example.upc2.repository.RepositoryDokter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted

class HomeDokterViewModel(
    private val repositoryDokter: RepositoryDokter
) : ViewModel() {


    val homeDokterUiState: StateFlow<HomeDokterUiState> = repositoryDokter.getAllDokter()
        .map { listDokter ->
            HomeDokterUiState(
                listDokter = listDokter.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(HomeDokterUiState(isLoading = true))
            delay(900)
        }
        .catch { exception ->
            emit(
                HomeDokterUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = exception.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeDokterUiState(isLoading = true)
        )
}

data class HomeDokterUiState(
    val listDokter: List<dokter> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
