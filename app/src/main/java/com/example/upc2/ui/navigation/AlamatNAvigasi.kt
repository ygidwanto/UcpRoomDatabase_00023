package com.example.upc2.ui.navigation

interface AlamatNAvigasi {
    val route: String
    // Navigasi Halaman Dokter
    object DestinasiDokter : AlamatNAvigasi{
        override val route = "dokter"
    }
    object DestinasiDetailDokter : AlamatNAvigasi {
        override val route = "detaildokter"
        const val ID_DOKTER = "iddokter"
        val routeWithArg = "$route/{$ID_DOKTER}"
    }
}