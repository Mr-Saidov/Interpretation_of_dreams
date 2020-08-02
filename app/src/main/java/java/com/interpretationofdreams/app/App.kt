package java.com.interpretationofdreams.app

import android.app.Application
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.com.interpretationofdreams.BuildConfig

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}