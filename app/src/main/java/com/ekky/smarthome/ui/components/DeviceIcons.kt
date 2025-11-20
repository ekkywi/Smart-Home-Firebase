package com.ekky.smarthome.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

fun getDeviceIcon(type: String): ImageVector {
    return when(type.lowercase()) {
        "lamp" -> Icons.Filled.WbSunny
        "ac" -> Icons.Filled.AcUnit
        "fan" -> Icons.Filled.WindPower
        "door" -> Icons.Filled.DoorFront
        "tv" -> Icons.Filled.Tv
        "purifier" -> Icons.Filled.Air
        else -> Icons.Filled.Devices
    }
}