package com.example.upc2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.upc2.data.dao.dokterDao
import com.example.upc2.data.dao.jadwalDao
import com.example.upc2.data.entity.dokter
import com.example.upc2.data.entity.jadwal

@Database(entities = [dokter::class, jadwal::class],version = 1, exportSchema = false)
abstract class rsDatabase : RoomDatabase(){

    //Mendefinisikan fungsi untuk mengakses data Mahasiswa
    abstract fun dokterDao() : dokterDao
    abstract fun jadwalDao() : jadwalDao

    companion object {
        @Volatile
        private var Instance : rsDatabase? = null

        fun getDataBase(context: Context): rsDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    rsDatabase::class.java, // Class database
                    name = "rsDatabase"
                )
                    .build().also { Instance = it }
            })
        }
    }
}
