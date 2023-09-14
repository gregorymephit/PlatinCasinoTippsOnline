package com.resrplatin.ccasi.notipps

import android.app.Application
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // One signal initialization.
        OneSignal.initWithContext(this, getString(R.string.one_signal_id))
        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(true)
        }
    }
}