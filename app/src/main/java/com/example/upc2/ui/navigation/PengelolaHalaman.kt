package com.example.upc2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.upc2.ui.viewmodel.HomeDokterUiState
import com.example.upc2.ui.viewmodel.UpdateJadwalViewModel
import com.example.upc2.viewmodel.DetJdwlViewModel
import com.example.upc2.viewmodel.DetailJadwalUiState
import com.example.upc2.ui.view.dokter.InsertDtrView
import com.example.upc2.ui.view.jadwal.DetailJadwalUiState
import com.example.upc2.ui.view.jadwal.UpdateJadwalViewModel

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = AlamatNAvigasi.DestinasiDokter.route) {

        // Home Dokter screen
        composable(
            route = AlamatNAvigasi.DestinasiDokter.route
        ) {
            HomeDokterUiState(
                onDetailClick = { id ->
                    navController.navigate("${AlamatNAvigasi.DestinasiDokter.route}/$id")
                    println("PengelolaHalaman: nim = $id")
                },
                onAddDokter = {
                    navController.navigate(AlamatNAvigasi.DestinasiDokter.route)
                },
                modifier = modifier
            )
        }

        // Insert Dokter screen
        composable(
            route = AlamatNAvigasi.DestinasiInsertDokter.route
        ) {
            InsertDtrView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        // Destinasi Jadwal screen with arguments
        composable(
            DestinasiJadwal.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiJadwal.id) {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(DestinasiJadwal.id)
            id?.let { idValue ->
                DetJdwlViewModel(
                    id = idValue
                )
                DetailJadwalUiState(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiJadwal.route}/$idValue")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        // Update Jadwal screen with arguments
        composable(
            DestinasiUpdateJadwal.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateJadwal.ID_JADWAL) {
                    type = NavType.StringType
                }
            )
        ) {
            val idJadwal = it.arguments?.getString(DestinasiUpdateJadwal.ID_JADWAL)
            idJadwal?.let { id ->
                UpdateJadwalViewModel(
                    onBack = {
                        navController.popBackStack()
                    },
                    onNavigate = {
                        navController.popBackStack()
                    },
                    modifier = modifier
                )
            }
        }
    }
}
