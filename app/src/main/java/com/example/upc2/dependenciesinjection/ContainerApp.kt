package com.example.upc2.dependeciesinjection

import android.content.Context
import com.example.upc2.data.database.rsDatabase
import com.example.upc2.repository.LocalRepositoryDokter
import com.example.upc2.repository.LocalRepositoryJadwal
import com.example.upc2.repository.RepositoryDokter
import com.example.upc2.repository.RepositoryJadwal

// Interface untuk menyimpan dependensi aplikasi
interface InterfaceContainerApp {
    val repositoryDokter: RepositoryDokter
    val repositoryJadwal: RepositoryJadwal
}

// Implementasi container untuk injeksi dependensi
class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryDokter: RepositoryDokter by lazy{
        LocalRepositoryDokter(rsDatabase.getDataBase(context).dokterDao())
    }

    override val repositoryJadwal: RepositoryJadwal by lazy {
        LocalRepositoryJadwal(rsDatabase.getDataBase(context).jadwalDao())
    }
}
