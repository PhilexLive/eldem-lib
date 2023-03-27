package com.philexliveprojects.eldemlib

import android.app.Application
import com.philexliveprojects.eldemlib.data.AppContainer
import com.philexliveprojects.eldemlib.data.AppDataContainer

class MainApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}