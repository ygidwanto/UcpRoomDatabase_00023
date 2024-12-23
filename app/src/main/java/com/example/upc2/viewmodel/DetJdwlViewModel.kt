package com.example.upc2.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upc2.data.entity.jadwal
import com.example.upc2.repository.RepositoryJadwal
import com.example.upc2.ui.navigation.DestinasiDetailJadwal
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetJdwlViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryJadwal: RepositoryJadwal,
) : ViewModel() {
    private val _idJadwal: String = checkNotNull(savedStateHandle[DestinasiDetailJadwal.ID_JADWAL])

    // StateFlow untuk menyimpan UI state detail jadwal
    val detailJadwalUiState: StateFlow<DetailJadwalUiState> = repositoryJadwal.getJadwalById(_idJadwal)
        .filterNotNull()
        .map { jadwal ->
            DetailJadwalUiState(
                detailJadwal = jadwal,
                isLoading = false
            )
        }
        .onStart {
            emit(DetailJadwalUiState(isLoading = true))
            delay(600) // Simulasi loading
        }
        .catch { exception ->
            emit(
                DetailJadwalUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = exception.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailJadwalUiState(
                isLoading = true
            )
        )

    // Fungsi untuk menghapus jadwal
    fun deleteJadwal() {
        val currentJadwal = detailJadwalUiState.value.detailJadwal
        if (currentJadwal != null) {
            viewModelScope.launch {
                repositoryJadwal.deleteJadwal(currentJadwal)
            }
        }
    }
}

// Data class untuk menyimpan UI state
data class DetailJadwalUiState(
    val detailJadwal: jadwal? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isDetailEmpty: Boolean
        get() = detailJadwal == null
    val isDetailNotEmpty: Boolean
        get() = detailJadwal != null
}
