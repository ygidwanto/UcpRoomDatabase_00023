package com.example.upc2.repository

import com.example.upc2.data.dao.dokterDao
import com.example.upc2.data.dao.jadwalDao
import com.example.upc2.data.entity.dokter
import com.example.upc2.data.entity.jadwal
import kotlinx.coroutines.flow.Flow

//
class LocalRepositoryDokter(
    private val dokterDao: dokterDao
) : RepositoryDokter{
    override suspend fun insertDokter(dokter: dokter) {
        dokterDao.insertDokter(dokter)
    }
    override fun getAllDokter(): Flow<List<dokter>> {
        return dokterDao.getAllDokter()
    }

    override fun getDokter(id: String): Flow<dokter> {
        return dokterDao.getDokter(id)
    }
}
// Repository Jadwal

class LocalRepositoryJadwal(
    private val jadwalDao: jadwalDao
) : RepositoryJadwal{
    override suspend fun insertJadwal(jadwal: jadwal) {
        jadwalDao.insertJadwal(jadwal)
    }

    override fun getAllJadwal(): Flow<List<jadwal>> {
        return jadwalDao.getAllJadwal()
    }

    override fun getJadwalById(id: String): Flow<jadwal> {
        return jadwalDao.getAllJadwal(id)
    }

   override suspend fun deleteJadwal(jadwal: jadwal) {
        jadwalDao.deleteJadwal(jadwal)
    }

    override suspend fun updateJadwal(jadwal: jadwal) {
        jadwalDao.updateJadwal(jadwal)
    }
}
