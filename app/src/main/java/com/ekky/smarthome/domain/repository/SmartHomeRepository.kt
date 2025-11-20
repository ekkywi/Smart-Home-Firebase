package com.ekky.smarthome.domain.repository

import com.ekky.smarthome.data.firebase.FirebaseService
import com.ekky.smarthome.data.model.RoomModel

class SmartHomeRepository(
    private val firebaseService: FirebaseService = FirebaseService()
) {

    fun getRooms(onSuccess: (List<RoomModel>) -> Unit) {
        firebaseService.getRooms(onSuccess)
    }

    fun getDevices(roomId: String, onSuccess: (Map<String, Any?>) -> Unit) {
        firebaseService.getDevices(roomId, onSuccess)
    }

    fun updateDevice(roomId: String, deviceId: String, data: Map<String, Any?>) {
        firebaseService.updateDevice(roomId, deviceId, data)
    }
}
