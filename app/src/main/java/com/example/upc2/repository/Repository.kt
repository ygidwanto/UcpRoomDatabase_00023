package com.example.upc2.repository

import com.example.upc2.data.entity.dokter
import com.example.upc2.data.entity.jadwal
import kotlinx.coroutines.flow.Flow

//Repository Dokter
interface RepositoryDokter {
    suspend fun insertDokter(dokter: dokter)
    fun getAllDokter(): Flow<List<dokter>>
    fun getDokter(id: String): Flow<dokter>
}

//Repository Jadwal
interface RepositoryJadwal {
    suspend fun insertJadwal (jadwal: jadwal)
    fun getAllJadwal(): Flow<List<jadwal>>
    fun getJadwalById(id: String): Flow<jadwal>
    suspend fun deleteJadwal(jadwal: jadwal)
    suspend fun updateJadwal(jadwal: jadwal)
}