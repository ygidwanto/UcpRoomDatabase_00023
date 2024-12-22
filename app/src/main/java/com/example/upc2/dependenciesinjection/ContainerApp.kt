package com.example.upc2.dependenciesinjection

import com.example.upc2.repository.RepositoryDokter
import com.example.upc2.repository.RepositoryJadwal

interface InterfaceContainerApp{
    val repositoryDokter: RepositoryDokter
    val repositoryJadwal: RepositoryJadwal
}