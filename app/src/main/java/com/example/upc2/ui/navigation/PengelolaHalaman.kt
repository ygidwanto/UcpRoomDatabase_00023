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
import com.example.upc2.viewmodel.DetJdwlViewModel
import com.example.upc2.ui.view.InsertDokterView
import com.example.upc2.ui.view.jadwal.DetailJadwalView
import com.example.upc2.ui.view.jadwal.UpdateJadwalView
import com.example.upc2.ui.navigation.AlamatNAvigasi
import com.example.upc2.ui.navigation.DestinasiJadwal
import com.example.upc2.ui.navigation.DestinasiUpdateJadwal
import com.example.upc2.view.DetailJadwalView
import com.example.upc2.view.InsertDokterView
import com.example.upc2.view.UpdateJadwalView

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
                onDetailClick = { id ->  // Navigate to detail dokter screen
                    navController.navigate("${AlamatNAvigasi.DestinasiDokter.route}/$id")
                    println("PengelolaHalaman: nim = $id")
                },
                onAddDokter = { // Navigate to Insert Dokter screen
                    navController.navigate(AlamatNAvigasi.DestinasiDokter.route)
                },
                modifier = modifier
            )
        }

        // Insert Dokter screen
        composable(
            route = AlamatNAvigasi.DestinasiDokter.route
        ) {
            InsertDokterView(
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
            DestinasiJadwal.route,
            arguments = listOf(
                navArgument(DestinasiJadwal.id) {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(DestinasiJadwal.id)  // Extract id from arguments
            id?.let { idValue ->
                DetJdwlViewModel(id = idValue) // Initialize ViewModel for jadwal
                DetailJadwalView(
                    onBack = {
                        navController.popBackStack()  // Go back onBack click
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiJadwal.route}/$idValue")  // Navigate to update page
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()  // Handle delete action
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
            val idJadwal = it.arguments?.getString(DestinasiUpdateJadwal.ID_JADWAL)  // Get ID from arguments
            idJadwal?.let { id ->
                UpdateJadwalView(
                    onBack = {
                        navController.popBackStack()  // Go back onBack click
                    },
                    onNavigate = {
                        navController.popBackStack()  // Navigate back after update
                    },
                    modifier = modifier
                )
            }
        }
    }
}
