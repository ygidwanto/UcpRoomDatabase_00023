package com.example.upc2.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.upc2.data.entity.jadwal
import com.example.upc2.ui.costumwidget.TopAppBar
import com.example.upc2.ui.viewmodel.HomeJadwalUiState
import com.example.upc2.ui.viewmodel.HomeJdwlViewModel
import com.example.upc2.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeJadwalView(
    viewModel: HomeJdwlViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddJadwal: () -> Unit = {},
    onDetailClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val homeUiState by viewModel.homeJadwalUiState.collectAsState() // Mengamati UI State
    val snackbarHostState = remember { SnackbarHostState() } // Snackbar Host
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                judul = "Daftar Jadwal Dokter",
                showBackButton = false,
                onBack = {}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddJadwal,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Jadwal"
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) } // Menambahkan Snackbar Host
    ) { innerPadding ->
        BodyHomeJadwalView(
            homeUiState = homeUiState,
            onClick = { id -> onDetailClick(id) },
            snackbarHostState = snackbarHostState,
            coroutineScope = coroutineScope,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeJadwalView(
    homeUiState: HomeJadwalUiState,
    onClick: (String) -> Unit = {},
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    when {
        homeUiState.isLoading -> {
            // Menampilkan indikator loading
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeUiState.isError -> {
            // Menampilkan pesan error menggunakan Snackbar
            LaunchedEffect(homeUiState.errorMessage) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = homeUiState.errorMessage,
                        actionLabel = "Dismiss"
                    )
                }
            }
        }

        homeUiState.listJadwal.isEmpty() -> {
            // Menampilkan pesan jika data kosong
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data Jadwal",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            // Menampilkan daftar jadwal
            ListJadwal(
                listJadwal = homeUiState.listJadwal,
                onClick = onClick,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListJadwal(
    listJadwal: List<jadwal>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(listJadwal) { jadwal ->
            CardJadwal(
                jadwal = jadwal,
                onClick = { onClick(jadwal.id) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardJadwal(
    jadwal: jadwal,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = jadwal.NamaDokter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "")
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = jadwal.TanggalKonsultasi + ", " + jadwal.Status,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

