package com.ekky.smarthome.data.model

data class DeviceModel(
    val name: String = "",
    val type: String = "",
    val status: String = "OFF",
    val temperature: Int? = null,
    val speed: Int? = null
)