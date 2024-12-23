package com.example.upc2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upc2.data.entity.jadwal
import com.example.upc2.repository.RepositoryJadwal
import com.example.upc2.ui.navigation.DestinasiUpdateJadwal
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateJadwalViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryJadwal: RepositoryJadwal
) : ViewModel() {

    var namaDokter by mutableStateOf("")
    var namaPasien by mutableStateOf("")
    var noHp by mutableStateOf("")
    var tanggalKonsultasi by mutableStateOf("")
    var status by mutableStateOf("")
    var snackBarMessage by mutableStateOf<String?>(null)
    var isEntryValid by mutableStateOf(true)

    private val _idJadwal: String = checkNotNull(savedStateHandle[DestinasiUpdateJadwal.ID_JADWAL])

    init {
        viewModelScope.launch {
            val jadwalEntity = repositoryJadwal.getJadwalById(_idJadwal)
                .filterNotNull()
                .first()
            loadJadwalData(jadwalEntity)
        }
    }

    private fun loadJadwalData(jadwal: jadwal) {
        namaDokter = jadwal.NamaDokter
        namaPasien = jadwal.NamaPasien
        noHp = jadwal.NoHp
        tanggalKonsultasi = jadwal.TanggalKonsultasi
        status = jadwal.Status
    }

    fun validateFields(): Boolean {
        val isNamaDokterValid = namaDokter.isNotEmpty()
        val isNamaPasienValid = namaPasien.isNotEmpty()
        val isNoHpValid = noHp.isNotEmpty()
        val isTanggalValid = tanggalKonsultasi.isNotEmpty()
        val isStatusValid = status.isNotEmpty()

        isEntryValid = isNamaDokterValid && isNamaPasienValid && isNoHpValid && isTanggalValid && isStatusValid

        if (!isEntryValid) {
            snackBarMessage = "Harap isi semua field dengan benar"
        }
        return isEntryValid
    }

    fun updateData() {
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryJadwal.updateJadwal(
                        jadwal(
                            id = _idJadwal,
                            NamaDokter = namaDokter,
                            NamaPasien = namaPasien,
                            NoHp = noHp,
                            TanggalKonsultasi = tanggalKonsultasi,
                            Status = status
                        )
                    )
                    snackBarMessage = "Data berhasil diupdate"
                } catch (e: Exception) {
                    snackBarMessage = "Data gagal diupdate: ${e.message}"
                }
            }
        }
    }

    fun resetSnackBarMessage() {
        snackBarMessage = null
    }
}
