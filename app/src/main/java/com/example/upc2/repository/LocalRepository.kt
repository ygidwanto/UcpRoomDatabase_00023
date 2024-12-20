package com.example.upc2.repository

import com.example.upc2.data.dao.dokterDao
import com.example.upc2.data.entity.dokter
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
