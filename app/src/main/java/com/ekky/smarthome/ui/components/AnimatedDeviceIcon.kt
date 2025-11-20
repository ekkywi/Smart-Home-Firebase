package com.ekky.smarthome.ui.components

import androidx.compose.animation.core.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.offset
import com.ekky.smarthome.ui.theme.*

@Composable
fun AnimatedDeviceIcon(type: String, isOn: Boolean) {

    val infinite = rememberInfiniteTransition(label = "")

    val rotation by infinite.animateFloat(
        initialValue = 0f,
        targetValue = if (isOn) 360f else 0f,
        animationSpec = infiniteRepeatable<Float>(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    val slowRotation by infinite.animateFloat(
        initialValue = 0f,
        targetValue = if (isOn) 360f else 0f,
        animationSpec = infiniteRepeatable<Float>(
            animation = tween(3000),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    val pulse by infinite.animateFloat(
        initialValue = 1f,
        targetValue = if (isOn) 1.15f else 1f,
        animationSpec = infiniteRepeatable<Float>(
            animation = tween(800),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val glow by infinite.animateFloat(
        initialValue = 1f,
        targetValue = if (isOn) 1.18f else 1f,
        animationSpec = infiniteRepeatable<Float>(
            animation = keyframes<Float> {
                durationMillis = 1300
                1.18f at 650
                1f at 1300
            },
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    val blink by infinite.animateFloat(
        initialValue = 1f,
        targetValue = if (isOn) 0.25f else 1f,
        animationSpec = infiniteRepeatable<Float>(
            animation = tween(700),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val shake by infinite.animateFloat(
        initialValue = -4f,
        targetValue = if (isOn) 4f else 0f,
        animationSpec = infiniteRepeatable<Float>(
            animation = tween(90),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val tintColor = when(type.lowercase()) {
        "lamp" -> if (isOn) LampColor else DeviceOff
        "fan" -> if (isOn) FanColor else DeviceOff
        "ac" -> if (isOn) AcColor else DeviceOff
        "door" -> if (isOn) DoorColor else DeviceOff
        "tv" -> if (isOn) TvColor else DeviceOff
        "purifier" -> if (isOn) PurifierColor else DeviceOff
        else -> if (isOn) AccentPrimary else DeviceOff
    }

    val icon = when(type.lowercase()) {
        "lamp" -> Icons.Filled.WbSunny
        "ac" -> Icons.Filled.AcUnit
        "fan" -> Icons.Filled.WindPower
        "door" -> Icons.Filled.DoorFront
        "tv" -> Icons.Filled.Tv
        "purifier" -> Icons.Filled.Air
        else -> Icons.Filled.Devices
    }

    when(type.lowercase()) {
        "fan" -> Icon(icon, null, modifier = Modifier.rotate(rotation), tint = tintColor)
        "purifier" -> Icon(icon, null, modifier = Modifier.rotate(slowRotation), tint = tintColor)
        "ac" -> Icon(icon, null, modifier = Modifier.scale(pulse), tint = tintColor)
        "lamp" -> Icon(icon, null, modifier = Modifier.scale(glow), tint = tintColor)
        "tv" -> Icon(icon, null, modifier = Modifier.alpha(blink), tint = tintColor)
        "door" -> Icon(icon, null, modifier = Modifier.offset(x = shake.dp), tint = tintColor)
        else -> Icon(icon, null, tint = tintColor)
    }
}