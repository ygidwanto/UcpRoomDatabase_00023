package com.example.upc2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dokter")
data class dokter(
    @PrimaryKey
    val id: String,
    val Nama: String,
    val Spesialis: String,
    val Klinik: String,
    val NoHp: String,
    val JamKerja: String
)
