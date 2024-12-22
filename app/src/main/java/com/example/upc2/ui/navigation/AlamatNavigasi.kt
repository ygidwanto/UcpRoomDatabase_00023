package com.example.upc2.ui.navigation

interface AlamatNAvigasi {
    val route: String
    // Navigasi Halaman Dokter
    object DestinasiDokter : AlamatNAvigasi{
        override val route = "dokter"
    }
    object DestinasiDetailDokter : AlamatNAvigasi {
        override val route = "detailDokter"
        const val ID_DOKTER = "idDokter"
        val routeWithArg = "$route/{$ID_DOKTER}"
    }
}

// Navigasi untuk halaman Jadwal
object DestinasiJadwal : AlamatNAvigasi {
    override val route = "jadwal"
}

object DestinasiDetailJadwal : AlamatNAvigasi {
    override val route = "detailJadwal"
    const val ID_JADWAL = "idJadwal"
    val routeWithArg = "$route/{$ID_JADWAL}"
}

object DestinasiUpdateJadwal : AlamatNAvigasi {
    override val route = "updateJadwal"
    const val ID_JADWAL = "idJadwal"
    val routeWithArg = "$route/{$ID_JADWAL}"
}

