package com.ekky.smarthome.ui.home

import androidx.lifecycle.ViewModel
import com.ekky.smarthome.data.firebase.FirebaseService
import com.ekky.smarthome.data.model.RoomModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    private val repository = FirebaseService()

    private val _rooms = MutableStateFlow<List<RoomModel>>(emptyList())
    val rooms: StateFlow<List<RoomModel>> = _rooms

    init {
        loadRooms()
    }

    private fun loadRooms() {
        repository.getRooms { roomList ->
            _rooms.value = roomList
        }
    }
}