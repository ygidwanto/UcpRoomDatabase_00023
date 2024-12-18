package com.example.upc2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.upc2.data.entity.dokter
import kotlinx.coroutines.flow.Flow

@Dao
interface dokterDao {
    @Insert
    suspend fun insertDokter(dokter: dokter)
    @Query("SELECT * FROM dokter ORDER BY nama ASC")
    fun getAllDokter(): Flow<List<dokter>>

    @Query("SELECT * FROM dokter WHERE id = :id")
    fun getDokter(id: String): Flow<dokter>
}