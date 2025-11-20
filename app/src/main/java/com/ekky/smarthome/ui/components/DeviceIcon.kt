package com.ekky.smarthome.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp

@Composable
fun DeviceIcon(type: String) {
    val icon = when (type.lowercase()) {
        "lamp", "light" -> Icons.Default.Lightbulb
        "ac" -> Icons.Default.AcUnit
        "fan" -> Icons.Default.Toys
        "door" -> Icons.Default.DoorFront
        "tv" -> Icons.Default.Tv
        "water" -> Icons.Default.Water
        "kitchen" -> Icons.Default.Kitchen
        "camera" -> Icons.Default.Videocam
        "doorlock" -> Icons.Default.DoorSliding
        "charger" -> Icons.Default.BatteryChargingFull
        else -> Icons.Default.Devices
    }

    Icon(
        imageVector = icon,
        contentDescription = type,
        modifier = Modifier.size(32.dp)
    )
}