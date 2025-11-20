package com.ekky.smarthome.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekky.smarthome.ui.theme.*
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.background
import androidx.compose.ui.draw.blur

@Composable
fun SliderDeviceCard(
    title: String,
    type: String,
    value: Int,
    range: IntRange,
    state: Boolean,
    onToggle: (Boolean) -> Unit,
    onValueChange: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(vertical = 8.dp)
            .background(
                if (state) AccentPrimary.copy(alpha = 0.25f) else AppBackground,
                shape = RoundedCornerShape(24.dp)
            )
            .blur(if (state) 20.dp else 0.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(vertical = 8.dp)
                .shadow(
                    elevation = if (state) 18.dp else 4.dp,
                    shape = RoundedCornerShape(24.dp),
                    ambientColor = AccentPrimary,
                    spotColor = AccentPrimary
                ),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = AppCard),
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnimatedDeviceIcon(type = type, isOn = state)
                    Spacer(Modifier.weight(1f))

                    Text(title, fontSize = 20.sp, color = TextPrimary, fontWeight = FontWeight.Bold)

                    Spacer(Modifier.weight(1f))

                    Switch(
                        checked = state,
                        onCheckedChange = onToggle,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = AccentPrimary,
                            checkedTrackColor = AccentPrimary.copy(alpha = 0.4f),
                            uncheckedThumbColor = AccentSecondary,
                            uncheckedTrackColor = AccentSecondary.copy(alpha = 0.4f)
                        )
                    )
                }

                Text(type.uppercase(), fontSize = 14.sp, color = TextSecondary)
                Spacer(Modifier.height(8.dp))

                Text(
                    text = if (type.lowercase() == "ac") "$value °C" else "Speed $value",
                    fontSize = 16.sp,
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = if (state) "Active" else "Inactive",
                    color = if (state) AccentPrimary else TextSecondary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(8.dp))

                Slider(
                    value = value.toFloat(),
                    onValueChange = { onValueChange(it.toInt()) },
                    valueRange = range.first.toFloat()..range.last.toFloat(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = SliderDefaults.colors(
                        thumbColor = AccentPrimary,
                        activeTrackColor = AccentPrimary,
                        inactiveTrackColor = AccentPrimary.copy(alpha = 0.3f)
                    )
                )
            }
        }
    }
}