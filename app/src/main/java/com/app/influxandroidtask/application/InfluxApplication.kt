package com.app.influxandroidtask.application

import android.app.Application

class InfluxApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: InfluxApplication
            private set
    }
}