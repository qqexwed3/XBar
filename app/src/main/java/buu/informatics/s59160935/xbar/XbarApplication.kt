package buu.informatics.s59160935.xbar

import android.app.Application
import timber.log.Timber

class XbarApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}