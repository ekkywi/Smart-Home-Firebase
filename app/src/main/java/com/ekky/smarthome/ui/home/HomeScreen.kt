package com.ekky.smarthome.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import com.ekky.smarthome.ui.theme.*
import com.ekky.smarthome.data.model.RoomModel
import androidx.compose.ui.Alignment

@Composable
fun HomeScreen(viewModel: HomeViewModel, onRoomClick: (String) -> Unit) {

    val rooms = viewModel.rooms.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(16.dp)
    ) {

        Text("Your Rooms", fontSize = 26.sp, color = TextBlack)

        Spacer(Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(rooms.value) { room ->
                RoomCard(
                    name = room.name ?: "",
                    deviceCount = room.devices?.size ?: 0,
                    onClick = { onRoomClick(room.id ?: "") }
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))

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

@Composable
fun RoomCard(name: String, deviceCount: Int, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = AppCard),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {
            Text(name, fontSize = 22.sp, color = TextPrimary)
            Spacer(Modifier.height(8.dp))
            Text("$deviceCount Devices", fontSize = 14.sp, color = TextSecondary)
        }
    }
}