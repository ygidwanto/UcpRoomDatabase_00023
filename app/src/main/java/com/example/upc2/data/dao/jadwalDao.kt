package com.example.upc2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.upc2.data.entity.jadwal
import kotlinx.coroutines.flow.Flow

@Dao
interface jadwalDao {
    @Insert
    suspend fun insertJadwal(jadwal: jadwal)
    @Query("SELECT * FROM jadwal ORDER BY NamaPasien")
    fun getAllJadwal(): Flow<List<jadwal>>

    @Query("SELECT * FROM jadwal WHERE id= :id")
    fun getAllJadwal(id: String): Flow<jadwal>

    @Delete
    suspend fun deleteJadwal(jadwal: jadwal)

    @Update
    suspend fun updateJadwal(jadwal: jadwal)
}