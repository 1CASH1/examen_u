package com.hugo.evaluation.view.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

//clasera para ejecutar el servicio en la ejecucion de la app
class BootDeviceReceivers : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            ContextCompat.startForegroundService(it, Intent(it, LocationService::class.java))
        }

    }
}