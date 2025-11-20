package com.ekky.smarthome.data.model

data class RoomModel(
    var id: String = "",
    var name: String = "",
    var devices: Map<String, DeviceModel>? = null
)