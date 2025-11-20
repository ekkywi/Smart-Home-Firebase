package com.ekky.smarthome.ui.roomdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ekky.smarthome.ui.components.DeviceCard
import com.ekky.smarthome.ui.components.SliderDeviceCard
import com.ekky.smarthome.ui.theme.AppBackground
import androidx.compose.runtime.collectAsState
import com.ekky.smarthome.ui.theme.*
import androidx.compose.ui.Alignment

@Composable
fun RoomDetailScreen(roomId: String) {

    val viewModel: RoomDetailViewModel = viewModel()
    val devices = viewModel.devices.collectAsState()

    LaunchedEffect(roomId) {
        viewModel.loadDevices(roomId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(16.dp)
    ) {

        Text(text = "Devices in $roomId", fontSize = 22.sp)

        devices.value.forEach { (id, device) ->

            when (device.type.lowercase()) {

                "ac" -> {
                    var localTemp by remember(device.temperature) { mutableStateOf(device.temperature ?: 24) }

                    SliderDeviceCard(
                        title = device.name ?: id,
                        type = device.type,
                        value = localTemp,
                        range = 16..30,
                        state = device.status == "ON",
                        onToggle = { newState ->
                            viewModel.updateDevice(roomId, id, if (newState) "ON" else "OFF")
                        },
                        onValueChange = { newTemp ->
                            localTemp = newTemp
                            viewModel.updateDeviceTemp(roomId, id, newTemp)
                        }
                    )
                }

                "fan" -> {
                    var localSpeed by remember(device.speed) { mutableStateOf(device.speed ?: 1) }

                    SliderDeviceCard(
                        title = device.name ?: id,
                        type = device.type,
                        value = localSpeed,
                        range = 1..5,
                        state = device.status == "ON",
                        onToggle = { newState ->
                            viewModel.updateDevice(roomId, id, if (newState) "ON" else "OFF")
                        },
                        onValueChange = { newSpeed ->
                            localSpeed = newSpeed
                            viewModel.updateDeviceSpeed(roomId, id, newSpeed)
                        }
                    )
                }

                else -> DeviceCard(
                    title = device.name ?: id,
                    type = device.type,
                    state = device.status == "ON",
                    onToggle = { newState ->
                        viewModel.updateDevice(roomId, id, if (newState) "ON" else "OFF")
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Developed by Mamalia Fullstack Team",
            fontSize = 12.sp,
            color = TextSecondary,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}