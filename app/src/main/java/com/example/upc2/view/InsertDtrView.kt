package com.example.upc2.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.upc2.ui.viewmodel.HomeDktrViewModel
import com.example.upc2.ui.viewmodel.HomeDokterUiState
import kotlinx.coroutines.launch

object DokterInsertDestinasi: HomeDokterUiState{
    const val route = "insert_dokter"
}

@Composable
fun InsertDokterView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeDktrViewModel = viewModel()
) {
    val uiState = viewModel.uiState // Ambil UI state dari ViewModel
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Observasi perubahan snackbarMessage
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = Modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Dokter"
            )
            InsertBodyDokter(
                uiState = uiState,
                onValueChange = { field, value ->
                    // Mengupdate state UI tanpa update data
                    viewModel.updateUiState(field, value)
                },
                onClick = {
                    // Arahkan ke layar lain setelah menambah data (contoh)
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun InsertBodyDokter(
    modifier: Modifier = Modifier,
    onValueChange: (String, String) -> Unit,
    uiState: HomeDokterUiState,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormDokter(
            nama = uiState.Nama,
            spesialis = uiState.Spesialis,
            klinik = uiState.Klinik,
            noHp = uiState.NoHp,
            jamKerja = uiState.JamKerja,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Tambah")
        }
    }
}

@Composable
fun FormDokter(
    nama: String,
    spesialis: String,
    klinik: String,
    noHp: String,
    jamKerja: String,
    onValueChange: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nama,
            onValueChange = { onValueChange("nama", it) },
            label = { Text("Nama Dokter") },
            placeholder = { Text("Masukkan Nama Dokter") },
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = spesialis,
            onValueChange = { onValueChange("spesialis", it) },
            label = { Text("Spesialis") },
            placeholder = { Text("Masukkan Spesialis") },
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = klinik,
            onValueChange = { onValueChange("klinik", it) },
            label = { Text("Klinik") },
            placeholder = { Text("Masukkan Klinik") },
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = noHp,
            onValueChange = { onValueChange("noHp", it) },
            label = { Text("Nomor HP") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { /* Handle Done */ }),
            placeholder = { Text("Masukkan Nomor HP") },
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = jamKerja,
            onValueChange = { onValueChange("jamKerja", it) },
            label = { Text("Jam Kerja") },
            placeholder = { Text("Masukkan Jam Kerja") },
        )
    }
}
