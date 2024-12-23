package com.example.upc2.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.upc2.ui.costumwidget.TopAppBar
import com.example.upc2.ui.viewmodel.UpdateJadwalViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UpdateJadwalView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateJadwalViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState = viewModel // Ambil UI state dari ViewModel
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Observasi perubahan snackBarMessage
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Long
                )
                viewModel.resetSnackBarMessage()  // Reset snackbar message
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                onBack = onBack,
                judul = "Edit Jadwal",
                showBackButton = true
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Isi body form untuk update jadwal
            InsertBodyJadwal(
                uiState = uiState,
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()) {
                            viewModel.updateData()
                            delay(600)
                            withContext(Dispatchers.Main) {
                                onNavigate() // Navigasi di main thread
                            }
                        } else {
                            // Tampilkan error jika validasi gagal
                            snackbarHostState.showSnackbar("Field tidak valid!")
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun InsertBodyJadwal(
    modifier: Modifier = Modifier,
    uiState: UpdateJadwalViewModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormJadwal(
            namaDokter = uiState.namaDokter,
            namaPasien = uiState.namaPasien,
            noHp = uiState.noHp,
            tanggalKonsultasi = uiState.tanggalKonsultasi,
            status = uiState.status
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Update Jadwal")
        }
    }
}

@Composable
fun FormJadwal(
    namaDokter: String,
    namaPasien: String,
    noHp: String,
    tanggalKonsultasi: String,
    status: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = namaDokter,
            onValueChange = {  },  // Tidak ada perubahan pada value
            label = { Text("Nama Dokter") },
            placeholder = { Text("Masukkan Nama Dokter") },
            readOnly = true  // Hanya menampilkan, tidak dapat diubah
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = namaPasien,
            onValueChange = {  },  // Tidak ada perubahan pada value
            label = { Text("Nama Pasien") },
            placeholder = { Text("Masukkan Nama Pasien") },
            readOnly = true  // Hanya menampilkan, tidak dapat diubah
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = noHp,
            onValueChange = {  },  // Tidak ada perubahan pada value
            label = { Text("Nomor HP") },
            placeholder = { Text("Masukkan Nomor HP") },
            readOnly = true  // Hanya menampilkan, tidak dapat diubah
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = tanggalKonsultasi,
            onValueChange = {  },  // Tidak ada perubahan pada value
            label = { Text("Tanggal Konsultasi") },
            placeholder = { Text("Masukkan Tanggal Konsultasi") },
            readOnly = true  // Hanya menampilkan, tidak dapat diubah
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = status,
            onValueChange = {  },  // Tidak ada perubahan pada value
            label = { Text("Status") },
            placeholder = { Text("Masukkan Status") },
            readOnly = true  // Hanya menampilkan, tidak dapat diubah
        )
    }
}
