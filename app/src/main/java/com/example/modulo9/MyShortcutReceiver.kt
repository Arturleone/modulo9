package com.example.modulo9

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon

class MyShortcutReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val shortcutManager = context.getSystemService(ShortcutManager::class.java)

        if (shortcutManager != null && shortcutManager.isRequestPinShortcutSupported) {
            val shortcutRegistration = ShortcutInfo.Builder(context, "shortcut_registration")
                .setShortLabel("Registro")
                .setLongLabel("Registrar uma nova reclamação")
                .setIntent(Intent(context, RegisterComplaintActivity::class.java))
                .build()

            val shortcutComplaints = ShortcutInfo.Builder(context, "shortcut_complaints")
                .setShortLabel("Reclamações")
                .setLongLabel("Ver as reclamações registradas")
                .setIntent(Intent(context, ComplaintsListActivity::class.java))
                .build()

            shortcutManager.setDynamicShortcuts(listOf(shortcutRegistration, shortcutComplaints))
        }
    }
}
