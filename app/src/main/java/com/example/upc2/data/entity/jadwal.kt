package com.example.upc2.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "jadwal",
    foreignKeys = [ForeignKey(
        entity = dokter::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )])
data class jadwal(
    @PrimaryKey
    val id: String,
    val NamaDokter: String,
    val NamaPasien: String,
    val NoHp : String,
    val TanggalKonsultasi: String,
    val Status : String
)
