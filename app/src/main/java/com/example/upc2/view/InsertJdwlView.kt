package com.example.upc2.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.upc2.ui.navigation.AlamatNAvigasi
import com.example.upc2.ui.viewmodel.PenyediaViewModel
import com.example.upc2.ui.viewmodel.UpdateJadwalViewModel
import kotlinx.coroutines.launch

object DestinasiInsertJadwal : AlamatNAvigasi {
    override val route: String = "insert_jadwal"
}

@Composable
fun InsertJadwalView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateJadwalViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    var tanggal by remember { mutableStateOf("") }
    var jam by remember { mutableStateOf("") }
    var keterangan by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            // Isi Body
            InsertBodyJadwal(
                tanggal = tanggal,
                jam = jam,
                keterangan = keterangan,
                onValueChange = { field, value ->
                    when (field) {
                        "tanggal" -> tanggal = value
                        "jam" -> jam = value
                        "keterangan" -> keterangan = value
                    }
                },
                onClick = {
                    coroutineScope.launch {
                        val success = viewModel.saveData()
                        if (success) {
                            snackbarHostState.showSnackbar("Jadwal berhasil disimpan")
                            onNavigate()
                        } else {
                            snackbarHostState.showSnackbar("Terjadi kesalahan, coba lagi")
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun InsertBodyJadwal(
    tanggal: String,
    jam: String,
    keterangan: String,
    onValueChange: (String, String) -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormJadwal(
            tanggal = tanggal,
            jam = jam,
            keterangan = keterangan,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Simpan")
        }
    }
}

@Composable
fun FormJadwal(
    tanggal: String,
    jam: String,
    keterangan: String,
    onValueChange: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = tanggal,
            onValueChange = { onValueChange("tanggal", it) },
            label = { Text("Tanggal") },
            placeholder = { Text("Masukkan Tanggal (yyyy-mm-dd)") },
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = jam,
            onValueChange = { onValueChange("jam", it) },
            label = { Text("Jam") },
            placeholder = { Text("Masukkan Jam (HH:mm)") },
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = keterangan,
            onValueChange = { onValueChange("keterangan", it) },
            label = { Text("Keterangan") },
            placeholder = { Text("Masukkan Keterangan") },
        )
    }
}
