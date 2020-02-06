package com.vvp.itunesinfo

import android.app.Application
import com.vvp.itunesinfo.di.components.DaggerDiComponent
import com.vvp.itunesinfo.di.components.DiComponent

class App: Application() {

    companion object{
        var diComponent: DiComponent? = null
    }


    override fun onCreate() {
        super.onCreate()

        diComponent = DaggerDiComponent.builder().build()
    }
}