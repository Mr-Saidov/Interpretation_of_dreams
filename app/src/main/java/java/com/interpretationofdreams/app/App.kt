package java.com.interpretationofdreams.app

import android.app.Application
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.com.interpretationofdreams.BuildConfig
import java.com.interpretationofdreams.util.copyAttachedDB

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)
        copyAttachedDB(this)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}