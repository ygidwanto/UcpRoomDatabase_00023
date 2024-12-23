package com.example.upc2

import android.app.Application
import com.example.upc2.dependeciesinjection.ContainerApp

class RsApp : Application(){
    lateinit var containerApp: ContainerApp // Fungsi menyimpan instance

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this) // Membuat instance ContainerApp
        //Instance adalah obj yang dibuat dari class
    }
}