package com.ekky.smarthome.data.firebase

import com.google.firebase.database.*
import com.ekky.smarthome.data.model.RoomModel

class FirebaseService {

    private val db = FirebaseDatabase.getInstance().reference

    fun getRooms(onSuccess: (List<RoomModel>) -> Unit) {
        db.child("rooms").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<RoomModel>()
                snapshot.children.forEach {
                    val room = it.getValue(RoomModel::class.java)
                    room?.id = it.key.orEmpty()
                    list.add(room!!)
                }
                onSuccess(list)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun getDevices(roomId: String, onSuccess: (Map<String, Any?>) -> Unit) {
        db.child("rooms").child(roomId).child("devices")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val map = mutableMapOf<String, Any?>()
                    snapshot.children.forEach { child ->
                        map[child.key!!] = child.value
                    }
                    onSuccess(map)
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }

    fun updateDevice(roomId: String, deviceId: String, data: Map<String, Any?>) {
        db.child("rooms")
            .child(roomId)
            .child("devices")
            .child(deviceId)
            .updateChildren(data)
    }
}
