package com.nnapps.uctest

import android.app.Application
import com.usercentrics.sdk.*

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        // SettingsID value would usually be extracted to CI and file not visible to github
        val options = UsercentricsOptions(settingsId = "gChmbFIdL")
        Usercentrics.initialize(this, options)
    }
}