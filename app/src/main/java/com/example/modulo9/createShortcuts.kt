package com.example.modulo9

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon

fun createShortcuts(context: Context) {
    val shortcutManager = context.getSystemService(ShortcutManager::class.java)

    if (shortcutManager != null && shortcutManager.isRequestPinShortcutSupported) {
        val registrationShortcut = ShortcutInfo.Builder(context, "shortcut_registration")
            .setShortLabel("SO TO TESTANDO 2.0")
            .setLongLabel("SO TO TESTANDO")
            .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher))
            .setIntent(Intent(context, RegisterComplaintActivity::class.java).apply {
                action = Intent.ACTION_VIEW
            })
            .build()

        val complaintsShortcut = ShortcutInfo.Builder(context, "shortcut_complaints")
            .setShortLabel(context.getString(R.string.shortcut_complaints_short_label))
            .setLongLabel(context.getString(R.string.shortcut_complaints_long_label))
            .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher))
            .setIntent(Intent(context, ComplaintsListActivity::class.java).apply {
                action = Intent.ACTION_VIEW
            })
            .build()

        shortcutManager.dynamicShortcuts = listOf(registrationShortcut, complaintsShortcut)
    }
}
