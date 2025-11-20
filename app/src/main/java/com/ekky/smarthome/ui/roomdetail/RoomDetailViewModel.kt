package com.ekky.smarthome.ui.roomdetail

import androidx.lifecycle.ViewModel
import com.ekky.smarthome.domain.repository.SmartHomeRepository
import com.ekky.smarthome.data.model.DeviceModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RoomDetailViewModel : ViewModel() {

    private val repository = SmartHomeRepository()

    private val _devices = MutableStateFlow<Map<String, DeviceModel>>(emptyMap())
    val devices: StateFlow<Map<String, DeviceModel>> = _devices

    fun loadDevices(roomId: String?) {
        repository.getDevices(roomId.orEmpty()) { deviceMap ->
            val deviceList = deviceMap.mapValues { (_, value) ->
                val values = value as Map<String, Any?>
                DeviceModel(
                    name = values["name"] as? String ?: "",
                    type = values["type"] as? String ?: "",
                    status = values["status"] as? String ?: "",
                    temperature = values["temperature"] as? Int,
                    speed = values["speed"] as? Int
                )
            }
            _devices.value = deviceList
        }
    }

    fun updateDevice(roomId: String, deviceId: String, status: String) {
        repository.updateDevice(roomId, deviceId, mapOf("status" to status))
    }

    fun updateDeviceTemp(roomId: String, deviceId: String, temperature: Int) {
        repository.updateDevice(roomId, deviceId, mapOf("temperature" to temperature))
    }

    fun updateDeviceSpeed(roomId: String, deviceId: String, speed: Int) {
        repository.updateDevice(roomId, deviceId, mapOf("speed" to speed))
    }
}
