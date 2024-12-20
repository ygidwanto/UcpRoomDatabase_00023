package com.example.upc2.repository

import com.example.upc2.data.dao.dokterDao
import com.example.upc2.data.dao.jadwalDao
import com.example.upc2.data.entity.dokter
import com.example.upc2.data.entity.jadwal
import kotlinx.coroutines.flow.Flow

//
class LocalRepositorydokter(
    private val dokterDao: dokterDao
) {
    suspend fun insertDokter(dokter: dokter) {
        dokterDao.insertDokter(dokter)
    }

    fun getAllDokter(): Flow<List<dokter>> {
        return dokterDao.getAllDokter()
    }

    fun getDokter(id: String): Flow<dokter> {
        return dokterDao.getDokter(id)
    }
}
// Repository Jadwal

class LocalRepositoryJadwal(
    private val jadwalDao: jadwalDao
) {
    suspend fun insertJadwal(jadwal: jadwal) {
        jadwalDao.insertJadwal(jadwal)
    }

    fun getAllJadwal(): Flow<List<jadwal>> {
        return jadwalDao.getAllJadwal()
    }

    fun getJadwalById(id: String): Flow<jadwal> {
        return jadwalDao.getAllJadwal(id)
    }

    suspend fun deleteJadwal(jadwal: jadwal) {
        jadwalDao.deleteJadwal(jadwal)
    }

    suspend fun updateJadwal(jadwal: jadwal) {
        jadwalDao.updateJadwal(jadwal)
    }
}
